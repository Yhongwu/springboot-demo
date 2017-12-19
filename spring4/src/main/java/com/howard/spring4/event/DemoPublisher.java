package com.howard.spring4.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by hongwu on 2017/12/19.
 */
@Component
public class DemoPublisher {

    @Autowired
    ApplicationContext context;

    public void publish(String msg) {
        context.publishEvent(new DemoEvent(this,msg));
    }
}
