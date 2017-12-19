package com.howard.spring4.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器 实现ApplicationListener并指定监听类型
 * 使用onApplicationEvent对消息进行监听处理
 * Created by hongwu on 2017/12/19.
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent>{


    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();
        System.out.println("demoListener:"+msg);
    }
}
