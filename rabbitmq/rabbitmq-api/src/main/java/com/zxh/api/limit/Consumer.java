package com.zxh.api.limit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

public class Consumer {
    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建Channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、声明交换器
        String exchangeName = "test_limit_exchange";
        channel.exchangeDeclare(exchangeName,BuiltinExchangeType.TOPIC,true,false,false,null);

        //5、声明队列
        String queueName = "test_limit_queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //6、队列绑定到交换器
        String routingKey = "limit.#";
        channel.queueBind(queueName,exchangeName,routingKey);

        //7、接收信息
        //1 限流方式  第一件事就是 autoAck设置为 false
        channel.basicQos(0,1,false);

        channel.basicConsume(queueName, false, new MyConsumer(channel));
    }
}
