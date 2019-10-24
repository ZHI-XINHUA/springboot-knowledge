package com.zxh.api.exchange.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Producer4FanoutExchange
 * @Description 消息发送者
 *       Exchange类型为fanout：它会把所有发送到该交换器的消息路由到所有与该交换器绑定的队列中
 * @Author xh.zhi
 * @Date 2019-10-24 15:45
 * @Version 1.0
 **/
public class Producer4FanoutExchange {
    public static void main(String[] args) throws Exception {
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();
        //2、创建连接
        Connection connection = CommonUtils.getConnection(connectionFactory);
        //3、创建channel
        Channel channel = CommonUtils.getChannel(connection);


        //4、发送消息
        //String exchange, String routingKey, boolean mandatory, boolean immediate, BasicProperties props, byte[] body
        String exchange = "test_fanout_exchange";//交换机名称
        String routingKey = "";//路由key，作为是Exchange类型为fanout，会发送消息到该交换器绑定的队列中，和路由key没有关联，所以这里可以设置为空
        AMQP.BasicProperties props = null;//消息属性

        for(int i = 0; i < 10; i ++) {
            String message = "Hello World RabbitMQ 4 FANOUT Exchange Message >>> "+i;//消息内容
            channel.basicPublish(exchange,routingKey,props,message.getBytes());

            TimeUnit.SECONDS.sleep(1);
        }


        //释放资源
        CommonUtils.releaseConnect(connection,channel);
    }
}
