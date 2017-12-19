package com.howard.spring4.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.howard.spring4.prepost")
public class PrePostConfig {
    /**
     * 配置bean的时候会开始执行初始化
     * 结束时执行销毁前的操作
     * @return
     */
    @Bean(initMethod="init",destroyMethod="destory")
    BeanWayService beanWayService(){
        return new BeanWayService();
    }

    @Bean
    JSR250WayService jsr250WayService(){
        return new JSR250WayService();
    }
}
