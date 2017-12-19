package com.howard.spring4.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring 通过任务执行器(TaskExecutor)来实现多线程和并发编程
 * ThreadPoolTaskExecutor可实现基于线程池的TaskExecutor
 * @EnableAsync开启多异步的支持
 * @Async声明为一个异步任务
 * 运行结果看到 任务一和任务二不一定交替运行
 * Created by hongwu on 2017/12/19.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService bean = context.getBean(AsyncTaskService.class);
        for (int i = 0 ; i < 10 ; i ++) {
            bean.executeAsyncTask(i);
            bean.executeAsyncTaskPlus(i);
        }
    }
}
