package com.bidanet.bdcms.plugin.mq;

/**
 * Created by xuejike on 2016/12/7.
 */
public interface MessageQueueCallback {
    void success();
    void failure();
}
