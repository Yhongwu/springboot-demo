package com.howard.springboot07.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Howard Yao on 2018/7/14.
 */
@Component
public class Producer {
    /**
     * springboot默认配置的temple
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 一个生产者一个消费者
     * @param message
     */
    public void producer1(String message) {
        rabbitTemplate.convertAndSend("queue1",message);
    }

    /**
     * 一个生产者多个消费者
     * @param message
     */
    public void producer2(String message) {
        rabbitTemplate.convertAndSend("queue2",message);
    }
    public void producer3(String message) {
        rabbitTemplate.convertAndSend("queue3",message);
    }
    /**
     * fanout
     * @param message
     */
    public void producer4(String message) {
        this.rabbitTemplate.convertAndSend("fanoutExchange","", message);
    }

    /***************direct测试 *************/
    /**
     * @param message
     */
    public void producerDirect1(String message) {
        this.rabbitTemplate.convertAndSend("directExchange","directA", message);
    }

    public void producerDirect2(String message) {
        this.rabbitTemplate.convertAndSend("directExchange","directB", message);
    }


    /***************topic 测试 *************/
    /**
     * 被queueTopic1消费
     * @param message
     */
    public void producerTopic1(String message) {
        this.rabbitTemplate.convertAndSend("topicExchange","topic.aa", message);
    }

    /**
     * 该生产者的消息理论上应该被queueTopic1和queueTopic3消费
     * @param message
     */
    public void producerTopic2(String message) {
        this.rabbitTemplate.convertAndSend("topicExchange","topic.C", message);
    }

    /**
     * 被queueTopic2消费
     * @param message
     */
    public void producerTopic3(String message) {
        this.rabbitTemplate.convertAndSend("topicExchange","aa.topic", message);
    }

    /**
     * 测试延迟队列
     * @param message
     */
    public void producerDelay(String message) {
        this.rabbitTemplate.convertAndSend("exchange-delay","routing_key", message);
        System.out.println(new Date() + " send message  : " + message);
    }

    /**
     * 测试延迟队列（插件方式）
     * @param message
     */
    public void producerDelayPlugins(String message) {
        this.rabbitTemplate.convertAndSend("exchange-delay","routing_key", message,new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置延迟时间
                message.getMessageProperties().setHeader("x-delay",6000);
                return message;
            }
        });
        System.out.println(new Date() + " send message  : " + message);
    }
}
