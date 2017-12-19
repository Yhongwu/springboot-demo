package com.howard.spring4.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 * Created by hongwu on 2017/12/19.
 */
public class DemoEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;
    private String msg;

    public DemoEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
