package com.howard.spring4.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * profile在不同环境下使用不同的配置文件
 * 主要通过Environment.activeProfiles来设置，也可通过@Profile指定
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        //先设置活动的profile，在注册bean配置类
        context.getEnvironment().setActiveProfiles("prod");
        context.register(ProfileConfig.class);
        context.refresh();

        DemoBean bean = context.getBean(DemoBean.class);
        System.out.println(bean.getContent());

        context.close();
    }
}
