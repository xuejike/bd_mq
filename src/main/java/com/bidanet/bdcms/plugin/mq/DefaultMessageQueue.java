package com.bidanet.bdcms.plugin.mq;

import com.alibaba.fastjson.JSON;
import com.bidanet.bdcms.core.common.SpringWebTool;
import com.bidanet.bdcms.plugin.mq.vo.MqBean;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by xuejike on 2016/12/7.
 */
public class DefaultMessageQueue implements MessageQueue {

    protected static Logger logger=Logger.getLogger(DefaultMessageQueue.class);
    protected String host;
    protected String tempFile;
    protected File saveFile;

    protected BlockingQueue<MqBean> mqBeans=new LinkedBlockingQueue();

    protected boolean isStart=false;


    public void start(){
        initTempFile();
        readData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                isStart=true;
                while (isStart){
                    try {
                        MqBean take = mqBeans.take();

                    } catch (InterruptedException e) {
                        logger.trace(e);
                    }
                }
            }
        }).start();
    }

    private void initTempFile() {
        String path = SpringWebTool.getRealPath(tempFile);
        saveFile = new File(path);
        if (!saveFile.exists()){
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        isStart=false;
        saveData();
    }


    private void readData(){
        try {
            if (!saveFile.exists()){
                saveFile.createNewFile();
            }else{
                String saveJson = FileUtils.readFileToString(saveFile);
                List<MqBean> mqBeanList = JSON.parseArray(saveJson, MqBean.class);
                mqBeans.addAll(mqBeanList);
            }

        } catch (IOException e) {
            logger.error("缓存文件读取失败");
            logger.trace(e);
        }
    }
    private void saveData(){
        String jsonString = JSON.toJSONString(mqBeans);
        try {
            if (!saveFile.exists()){
                saveFile.createNewFile();
            }
            FileUtils.writeStringToFile(saveFile,jsonString);
            logger.info("缓存消息成功->"+saveFile.getPath());
        } catch (IOException e) {
            logger.error("缓存文件保存失败");
            logger.trace(e);
        }
    }

    @Override
    public void singleSend(String tag, Object obj) {

    }

    @Override
    public boolean syncSend(String tag, Object obj) {
        return false;
    }

    @Override
    public void asyncSend(String tag, Object obj, MessageQueueCallback messageQueueCallback) {

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTempFile() {
        return tempFile;
    }

    public void setTempFile(String tempFile) {
        this.tempFile = tempFile;
    }
}
