package com.howard.springboot02.tomcat;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 通过代码方式配置通用servlet容器 tomcat jett等
 * 可以实现EmbeddedServletContainerCustomizer
 * 也可以将其以@component的方式配置于Springboot02Application中，但要以static方式
 * Created by hongwu on 2017/12/23.
 */
/*@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer{
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8080);
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html")); //配置404页面
        container.setSessionTimeout(10, TimeUnit.MINUTES); //配置过期时间
    }
}*/
