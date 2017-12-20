package com.howard.springmvc4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 配置类
 */
@Configuration
@EnableWebMvc //开启一些默认配置如ViewResolver和MessageConverter
@ComponentScan("com.howard.springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter{
    /**
     * 配置jsp的ViewResolver
     * 用来映射路径和实际页面
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //不管html页面放在哪里，编译后都是放在/WEB-INF/classes/views/下
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
}
