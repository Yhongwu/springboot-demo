package com.howard.springmvc4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * WebApplicationInitializer是spring提供用来配置servlet3.0+配置的接口 从而实现替代web.xml
 * 实现此接口会自动被SpringServletContainerInitializer（用来启动servlet3.0容器）获取
 */
public class WebInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        //将配置类和当前servletContext关联
        context.register(MyMvcConfig.class);
        context.setServletContext(servletContext);

        //注册springmvc的DispatcherServlet
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);

    }
}
