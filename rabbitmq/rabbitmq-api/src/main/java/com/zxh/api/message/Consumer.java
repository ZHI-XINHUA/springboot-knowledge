package com.zxh.api.message;

import com.rabbitmq.client.*;
import com.zxh.api.CommonUtils;

import javax.xml.bind.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Consumer
 * @Description 消息消费者
 *   AMQP.BasicProperties
 * @Author xh.zhi
 * @Date 2019-10-25 15:34
 * @Version 1.0
 **/
public class Consumer {

    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建Channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、声明交换器
        String exchangeName = "test_message_exchange";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true,false,null);

        //5、声明队列
        String queueName = "test_message_queuq";
        channel.queueDeclare(queueName,true,false,false,null);

        //6、队列和交换器绑定
        String routingKey = "test.message.#";
        channel.queueBind(queueName,exchangeName,routingKey);

        //7、接收消息
        DeliverCallback deliverCallback = (consumerTag,delivery) ->{
            AMQP.BasicProperties properties =  delivery.getProperties();
            System.out.println("test="+properties.getHeaders().get("test").toString());

            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println("接收到信息："+message);
        };
        channel.basicConsume(queueName,true,deliverCallback,consumerTag -> {});
    }
}
