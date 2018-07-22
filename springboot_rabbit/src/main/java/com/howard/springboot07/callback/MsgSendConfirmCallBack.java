package com.howard.springboot07.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * Created by Howard Yao on 2018/7/15.
 */
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息确认成功cause"+cause);
        } else {
            //处理丢失的消息
            System.out.println("消息确认失败:"+correlationData.getId()+"#cause"+cause);
        }
    }
}