package com.howard.springboot07.consumer.test4;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Howard Yao on 2018/7/14.
 */
@Component
@RabbitListener(queues = "fanout.B")
public class FanoutConsumer2 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("consumer2: " + message);
    }
}
