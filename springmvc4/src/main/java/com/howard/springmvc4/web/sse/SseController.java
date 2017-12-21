package com.howard.springmvc4.web.sse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Server send event
 * 服务端推送
 * 方式1：利用浏览器对象，基于sse的服务端推送技术
 */
@Controller
public class SseController {

    /**
     * 每隔5秒推送
     * text/event-stream ：服务端sse的支持
     * @return
     */
    @RequestMapping(value = "/push",produces = "text/event-stream")
    public @ResponseBody String push() {
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3" + r.nextInt() + "\n\n";
    }
}
