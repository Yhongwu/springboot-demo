package com.howard.springboot06.receive;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 定义消息的监听
 */
@Component
public class Receiver {
    /**
     * @JmsListener是spring4.1提供的新特性 可简化jms开发
     * destination指定监听的目的地
     * 即发送到这个目的地的消息都会在这里进行处理
     * @param message
     */
    @JmsListener(destination = "my-destination")
    public void receiveMessage(String message) {
        System.out.println("接受消息："+message);
    }
}
