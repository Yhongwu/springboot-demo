package com.howard.springboot07.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by Howard Yao on 2018/7/15.
 */
public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        System.out.println("确认后回调return--message:"+new String(message.getBody())+",replyCode:"+replyCode+",replyText:"
                +replyText+",exchange:"+exchange+",routingKey:"+routingKey);
    }
}
