package com.howard.springboot02;

import com.howard.springboot02.Filter.MyFilter;
import com.howard.springboot02.Listener.MyListener;
import com.howard.springboot02.servlet.MyServlet;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by hongwu on 2017/12/22.
 */

/**
 * springboot默认为web自动配置了很多东西
 * 可以在该类上加上@EnableWebMvc注解，这样springboot的配置会无效 我们可以完全自定义自己的配置
 * 如果不加 则默认的springboot和自己新增的配置同时生效
 */
@Configuration
//@ServletComponentScan(value = "com.howard.springboot02")  //扫描filter、listener、servlet注解
public class MyWebMvcConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /**
         * 默认/映射到/index.html
         * 这里的配置并不会影响原有的
         */
        registry.addViewController("xxx").setViewName("xxx");
    }
    /**************************过滤器 filter**********************************/
    /**
     * 可以使用下面@bean配置注册过滤器、监听器、servlet
     * 也可以在MyFilter(或相应注解)上加上@WebFilter(或相应注解)和在当前配置类上加上@ServletComponentScan扫描来实现同样效果
     *
     */
    /**
     * 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        //过滤路径
        ArrayList<String> urls = new ArrayList<>();
        urls.add("/test");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(urls);
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
    /**
     * 监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>(new MyListener());
    }
    /**
     * servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean () {
        return new ServletRegistrationBean(new MyServlet(),"/myservlet");
    }


    /*******************************servlet容器配置****************************************/
    /**
     * servlet容器通用配置
     * 可在此配置 也可另新建类配置CustomServletContainer.java
     * 在这里配置注意应该设置为static类
     * 测试发现：该配置优先级比application.properties高
     */
    /*@Component
    public static class CustomServletContainer implements EmbeddedServletContainerCustomizer {
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.setPort(8080);
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html")); //配置404页面
            container.setSessionTimeout(10, TimeUnit.MINUTES); //配置过期时间
        }
    }*/
    /**
     * 可在此配置 也可在application.properties配置
     * 特定tomcat配置
     * 测试发现：CustomServletContainer中的通用配置覆盖了这里的配置
     * @return
     */
    /*@Bean
    public EmbeddedServletContainerFactory servletContainerFactory(){
        TomcatEmbeddedServletContainerFactory factory =
                new TomcatEmbeddedServletContainerFactory();
        factory.setPort(8888);
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        return factory;
    }*/
}
