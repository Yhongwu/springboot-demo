package com.howard.spring4.taskscheduler;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by hongwu on 2017/12/19.
 */
@Configuration
@ComponentScan("com.howard.spring4.taskscheduler")
@EnableScheduling //开启对计划任务的支持
public class TaskSchedulerConfig {

}
