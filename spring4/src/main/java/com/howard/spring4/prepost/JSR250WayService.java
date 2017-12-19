package com.howard.spring4.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 注解方式：利用jsr-250的方式
 */
public class JSR250WayService {
    //在构造函数之后执行
    @PostConstruct
    public void init(){
        System.out.println("jsr250-init-method");
    }

    public JSR250WayService() {
        super();
        System.out.println("初始化构造函数-JSR250WayService");
    }
    //在销毁前执行
    @PreDestroy
    public void destroy(){
        System.out.println("jsr250-destory-method");
    }
}
