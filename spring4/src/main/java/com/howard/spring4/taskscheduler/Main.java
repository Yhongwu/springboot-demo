package com.howard.spring4.taskscheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

/**
 * 计划任务TaskScheduler
 * Created by hongwu on 2017/12/19.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);

    }
}
