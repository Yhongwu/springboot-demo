package com.howard.springboot07.consumer.test3;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * Created by Howard Yao on 2018/7/15.
 */
public class MyConsumer implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        byte[] body = message.getBody();
        //处理业务逻辑
        System.out.println("consumer-receive : " + new String(body));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);     
    }
}
