package com.howard.springboot07.consumer.test6;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Howard Yao on 2018/7/14.
 */
@Component
@RabbitListener(queues = "topic.A")
public class TopicConsumer1 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("consumer1(topic.A): " + message);
    }
}
