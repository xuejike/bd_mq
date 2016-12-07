package com.bidanet.bdcms.plugin.mq.vo;

import com.bidanet.bdcms.plugin.mq.vo.status.NotificationStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by xuejike on 2016/12/7.
 */
@Entity
@Table(name = "_plugin_mq_bean")
public class MqBean {
    /**
     * 通知标题
     */
    private String title;
    /**
     * 标签
     */
    private String tag;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 接收时间
     */
    private long receiveTime;
    /**
     * 参数JSON
     */
    private String params;

    private NotificationStatus status;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Column(name = "create_time")
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Column(name = "receive_time")
    public long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(long receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Type(type = "text")
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Enumerated(EnumType.ORDINAL)
    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
}
