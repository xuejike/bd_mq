package com.bidanet.bdcms.plugin.mq;

/**
 * Created by xuejike on 2016/12/7.
 */
public interface MessageQueue {
    /**
     * 单发
     */
    void singleSend(String tag,Object obj);

    /**
     * 同步发送
     */
    boolean syncSend(String tag,Object obj);

    /**
     * 异步发送
     */
    void asyncSend(String tag,Object obj,MessageQueueCallback messageQueueCallback);

}
