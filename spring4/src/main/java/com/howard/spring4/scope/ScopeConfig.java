package com.howard.spring4.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * bean的scope
 * 默认singleton 单例
 * 可选prototype 每次创建一个、request:每一个http request创建一个 session：每一个http session创建一个
 *
 */
@Configuration
@ComponentScan("com.howard.spring4.scope")
public class ScopeConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ScopeConfig.class);
        DemoSingletonService s1 = context.getBean(DemoSingletonService.class);
        DemoSingletonService s2 = context.getBean(DemoSingletonService.class);
        DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);
        System.out.println(s1.equals(s2)); //true
        System.out.println(p1.equals(p2)); //false
    }

}
