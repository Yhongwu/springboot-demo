package com.howard.springboot07.consumer.test8;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Howard Yao on 2018/7/14.
 */
@Component
@RabbitListener(queues = "queue-delay")
public class DalayPluginsConsumer {

    @RabbitHandler
    public void process(String message) {
        System.out.println(new Date() + " received : " + message);
    }
}
