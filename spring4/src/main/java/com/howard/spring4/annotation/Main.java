package com.howard.spring4.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 组合注解
 * Created by hongwu on 2017/12/19.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);
        DemoService bean = context.getBean(DemoService.class);
        bean.outputResult();

        context.close();
    }
}
