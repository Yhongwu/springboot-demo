package com.howard.spring4.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 任务类
 * 这里开启使用异步执行
 * Created by hongwu on 2017/12/19.
 */
@Service
public class AsyncTaskService {

    @Async //使用异步方式执行 前提 在config中配置支持异步并配置线程异步执行器
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务一："+i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务二："+(i + 1));
    }
}
