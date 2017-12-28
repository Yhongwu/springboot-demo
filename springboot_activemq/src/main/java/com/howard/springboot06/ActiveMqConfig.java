package com.howard.springboot06;

import com.howard.springboot06.msg.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 * 实现CommandLineRunner用于程序启动后执行代码 通过重写run方法实现
 */
@Configuration
public class ActiveMqConfig implements CommandLineRunner{

    /**
     * springboot默认配置好了JmsTemplate
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 在消息代理上定义了一个my-destination
     * 通过jmsTemplate向my-destination发送了一个消息
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        jmsTemplate.send("my-destination",new Msg());
    }
}
