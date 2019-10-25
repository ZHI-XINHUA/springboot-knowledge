package com.zxh.api.myconsumer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

/**
 * @ClassName Consumer
 * @Description 消费者
 * @Author xh.zhi
 * @Date 2019-10-25 17:51
 * @Version 1.0
 **/
public class Consumer {

    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、声明交换器
        String exchangName = "test_myconsumer_exchange";
        channel.exchangeDeclare(exchangName, BuiltinExchangeType.TOPIC,true,false,null);

        //5、声明队列
        String queueName = "test_myconsumer_queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //6、队列绑定到交换器上
        String routingKey = "test.myconsumer.#";
        channel.queueBind(queueName,exchangName,routingKey);

        //7、接收消息
        channel.basicConsume(queueName,new MyConsumer(channel));
    }
}
