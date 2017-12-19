package com.howard.spring4.annotation;

import org.springframework.stereotype.Service;

/**
 * Created by hongwu on 2017/12/19.
 */
@Service
public class DemoService {
    public void outputResult() {
        System.out.println("从组合注解获取bean");
    }
}
