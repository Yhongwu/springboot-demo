package com.howard.spring4.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * 自定义组合注解
 * 将@Configuration 和 @ComponentScan 合为一个@WiselyConfiguration
 * Created by hongwu on 2017/12/19.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@ComponentScan
public @interface WiselyConfiguration {

    //覆盖ComponentScan中的value参数
    String[] value() default {};
}
