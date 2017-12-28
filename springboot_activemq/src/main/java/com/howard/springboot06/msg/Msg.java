package com.howard.springboot06.msg;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 定义jms发送的消息
 * 需实现MessageCreator并重写createMessage
 */
public class Msg implements MessageCreator{
    @Override
    public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage("activemq测试消息");
    }
}
