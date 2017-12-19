package com.howard.spring4.aop;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring4下注解+配置方式实现aop
 */
public class Main {
    //psvm
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
        demoAnnotationService.add();
        demoMethodService.add();
        context.close();

    }
}
