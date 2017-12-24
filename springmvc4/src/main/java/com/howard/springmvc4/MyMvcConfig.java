package com.howard.springmvc4;

import com.howard.springmvc4.interceptor.DemoInterceptor;
import com.howard.springmvc4.messageconverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * 配置类
 */
@Configuration
@EnableWebMvc //开启一些默认配置如ViewResolver和MessageConverter
@EnableScheduling //开启计划任务
@ComponentScan("com.howard.springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter{

    /*********************************jsp映射ViewResolver**************************************************/
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




    /*********************************静态资源配置**************************************************/
    /**
     * 资源配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * addResourceHandler ：文件放置的目录
         * addResourceLocations：对外暴露的访问路径
         */
        registry.addResourceHandler("/assets/**").addResourceLocations(
                "classpath:/assets/");// 3

    }
    /*********************************配置拦截器**************************************************/
    /**
     * 配置拦截器bean
     * @return
     */
    @Bean
    public DemoInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {// 2
        registry.addInterceptor(demoInterceptor());
    }
    /********************************* 无任何处理的控制器跳转**************************************************/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //对于没有任何处理的路径映射 可在此配置而不需要去写一个controller
        registry.addViewController("index").setViewName("index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
        /**************************sse服务端推送*****************************/
        registry.addViewController("/sse").setViewName("/sse");
        //服务端推送第二种方式：需要在WebInitializer中开启对异步的支持
        registry.addViewController("/async").setViewName("/async");

    }
    /********************************* 参数配置**************************************************/
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //忽略路径参数中的点
        //比如：xxx?a=xx.yy 正常情况.yy会被忽略
        //配置完后 得到的参数是xx.yy
        configurer.setUseSuffixPatternMatch(false);
    }
    /********************************* 文件上传相关参数配置**************************************************/
    /**
     * 文件上传相关参数配置
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver () {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //设置最大上传文件大小
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }

    /********************************* 配置自定义MessageConverter**************************************/
    /**
     * 配置自定义MessageConverter bean
     * @return
     */
    @Bean
    public MyMessageConverter myMessageConverter() {
        return new MyMessageConverter();
    }

    /**
     * configureMessageConverters
     * 重载该方法会覆盖掉springmvc默认注册的多个HttpMessageConverter
     * 所以建议使用extendMessageConverters
     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//    }

    /**
     * 仅添加一个自定义的HttpMessageConverter而不会覆盖默认注册的HttpMessageConverter
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(myMessageConverter());
    }


    //查看更多配置情况
    //WebMvcConfigurerAdapter
    //WebMvcConfigurer
}
