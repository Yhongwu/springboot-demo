package com.howard.springboot07;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 实现CommandLineRunner指定程序启动要运行的代码 通过重写run实现
 */
@Configuration
public class RabbitConfig implements CommandLineRunner{

    /**
     * springboot默认配置好
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 目的地队列
     * @return
     */
//    @Bean
//    public Queue queue() {
//        return new Queue("my-queue");
//    }

    @Override
    public void run(String... args) throws Exception {
        rabbitTemplate.convertAndSend("my-queue","Rabbit测试消息");
    }
}
