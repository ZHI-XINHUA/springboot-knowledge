package com.zxh.api.ack;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

/**
 * 消费者
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建Channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、声明交换器
        String exhangeName = "test_ack_exchange";
        channel.exchangeDeclare(exhangeName,BuiltinExchangeType.TOPIC,true,false,null);

        //5、声明队列
        String queueName = "test_ack_queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //6、队列绑定到交换器
        String routingKey = "ack.#";
        channel.queueBind(queueName,exhangeName,routingKey);

        //7、消费消息
        // 手工签收 必须要关闭 autoAck = false
        channel.basicConsume(queueName,false,new MyConsumer(channel));
    }
}
