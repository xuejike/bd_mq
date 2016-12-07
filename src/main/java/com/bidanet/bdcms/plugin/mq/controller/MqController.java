package com.bidanet.bdcms.plugin.mq.controller;

import com.bidanet.bdcms.core.bean.ApiResult;
import com.bidanet.bdcms.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通知控制器
 */
@Controller("plugin_mq")
@RequestMapping("/plugin/mq")
public class MqController extends BaseController {

    @RequestMapping("/deal")
    public ApiResult deal(String tag,String params){

        return ApiResult.success("处理成功");
    }
}
