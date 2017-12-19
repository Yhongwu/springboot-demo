package com.howard.spring4.aop;

import org.springframework.stereotype.Service;

/**
 * 将被使用注解的方式拦截的类
 */
@Service
public class DemoAnnotationService {
    @Action(name = "被拦截的类")
    public  void add(){}
}
