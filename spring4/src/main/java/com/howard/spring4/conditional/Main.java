package com.howard.spring4.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Conditional 根据某条件创建bean
 * Created by hongwu on 2017/12/19.
 */

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConditionConifg.class);
        ListService bean = context.getBean(ListService.class);

        System.out.println(context.getEnvironment().getProperty("os.name")
                + "系统下的列表命令为: "
                + bean.showListCmd());

        context.close();
    }
}
