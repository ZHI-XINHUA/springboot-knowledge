package com.zxh.api.returnlinstener;

import com.rabbitmq.client.*;
import com.zxh.api.CommonUtils;

public class Consumer {
    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建Channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、声明exhange
        String exchangeName = "test_return_exchange";
        channel.exchangeDeclare(exchangeName,BuiltinExchangeType.TOPIC,true,false,null);

        //5、声明队列
        String queueName = "test_return_queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //6、绑定队列
        String rountingkey = "return.#";
        channel.queueBind(queueName,exchangeName,rountingkey);

        DeliverCallback deliverCallback = (consumerTay,delivery) ->{
            System.err.println("接收信息："+new String(delivery.getBody()));
        };
        channel.basicConsume(queueName,true,deliverCallback,(consumerTag)->{});
    }
}
