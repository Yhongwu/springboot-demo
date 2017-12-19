package com.howard.spring4.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * bean的初始化和销毁
 * 两种方式：
 * java配置和jsr-250注解方式(需要引入jsr250包)
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PrePostConfig.class);

        BeanWayService beanWayService = context.getBean(BeanWayService.class);
        JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);

        context.close();
    }
}
