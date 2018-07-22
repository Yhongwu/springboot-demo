package com.howard.springboot07.receive;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收消息
 */
/*@Component
public class Receiver {

    *//**
     * 使用@RabbitListener来监听queues指定的目的地
     * 接收往目的地queues的消息
     * @param message
     *//*
    @RabbitListener(queues = "my-queue")
    public void receiveMessage(String message) {
        System.out.println("接收到的消息："+message);
    }
}*/
