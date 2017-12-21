package com.howard.springmvc4.web.sse;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.howard.springmvc4.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 服务端推送技术
 * 基于servlet3.0+实现的异步方法特性
 */
@Controller
public class AysncController {

    @Autowired
    private PushService pushService;

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredResult () {
        return pushService.getAsyncUpdate();
    }
}
