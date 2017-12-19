package com.howard.spring4.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //声明该类为配置类 相当于在xml中进行配置
@ComponentScan("com.howard.spring4.aop") //扫描包
@EnableAspectJAutoProxy //开启spring对AspectJ代理的支持
public class AopConfig {
}
