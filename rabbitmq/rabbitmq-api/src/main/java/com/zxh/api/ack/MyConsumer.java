package com.zxh.api.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 自定义消费者接受信息类
 */
public class MyConsumer extends DefaultConsumer {

    private Channel channel;

    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("接受消息："+new String(body));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int num = Integer.parseInt(properties.getHeaders().get("num").toString());
        if(num==1){
            //第三个参数设置是否重回队列
            channel.basicNack(envelope.getDeliveryTag(),false,true);
        }else {
            channel.basicAck(envelope.getDeliveryTag(), false);
        }

    }
}
