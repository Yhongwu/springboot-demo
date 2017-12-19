package com.howard.spring4.taskscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Scheduled 声明为计划任务
 * Created by hongwu on 2017/12/19.
 */
@Service
public class ScheduledTaskService {

    private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)  //5秒一次
    public void reporCurrentTime() {
        System.out.println("每隔5秒执行一次："+df.format(new Date()));
    }
    @Scheduled(cron = "0 28 22 ? * *")
    public void fixTimeExecution() {
        System.out.println("在指定时间："+ df.format(new Date())+"执行");
    }
}
