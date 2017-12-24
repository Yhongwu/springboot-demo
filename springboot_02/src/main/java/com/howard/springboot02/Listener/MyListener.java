package com.howard.springboot02.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.sound.midi.Soundbank;

/**
 * 自定义监听器
 * Created by hongwu on 2017/12/23.
 */
//@WebListener(value = "myListerner") //可使用该注解方式 也可在MyWebMvcConfig进行配置bean
public class MyListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("监听器初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("监听器被销毁");
    }
}
