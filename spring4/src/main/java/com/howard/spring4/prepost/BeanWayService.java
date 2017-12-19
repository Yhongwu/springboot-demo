package com.howard.spring4.prepost;

/**
 * 使用java配置方式（@Bean）来在bean开始和销毁时执行某些操作
 */
public class BeanWayService {

    public void init() {
        System.out.println("@Bean-init-method");
    }
    public BeanWayService() {
        super();
        System.out.println("初始化构造函数-BeanWayService");
    }

    public  void destory() {
        System.out.println("@Bean-destory-method");
    }
}
