package com.zxh.api.exchange.direct;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Producer4DirectExchange
 * @Description Exchange类型为direct的生产者
 *   direct 类型的交换器路由规则：消息路由到那些BindingKey和RountingKey完全匹配的队列中
 * @Author xh.zhi
 * @Date 2019-10-25 9:01
 * @Version 1.0
 **/
public class Producer4DirectExchange {

    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();
        //2、创建连接
        Connection connection = CommonUtils.getConnection(connectionFactory);
        //3、创建channel
        Channel channel = CommonUtils.getChannel(connection);


        //4、发送消息
        //String exchange, String routingKey, boolean mandatory, boolean immediate, BasicProperties props, byte[] body
        String exchange = "test_direct_exchange";//交换机名称
        String routingKey = "test_direct_key";//路由key

        //设置消息属性
        AMQP.BasicProperties properties = new AMQP.BasicProperties();
        for(int i = 0; i < 10; i ++) {
            String message = "Hello World RabbitMQ 4 DIRECT Exchange Message >>> "+i;//消息内容
            channel.basicPublish(exchange,routingKey,properties,message.getBytes());

            TimeUnit.SECONDS.sleep(1);
        }


        //释放资源
        CommonUtils.releaseConnect(connection,channel);
    }
}
