package com.howard.spring4.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring的事件监听
 * Bean与Bean之间的通信
 * 当一个bean执行完想让另一个bean知道并执行某操作 可以使用监听
 * 1、自定义事件：集成ApplicationEvent
 * 2、定义事件监听器：实现ApplicationListener
 * 3、使用容器发布事件
 * Created by hongwu on 2017/12/19.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher bean = context.getBean(DemoPublisher.class);
        bean.publish("hello application event");
        context.close();
    }
}
