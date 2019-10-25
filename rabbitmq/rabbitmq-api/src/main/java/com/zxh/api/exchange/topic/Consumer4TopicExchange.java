package com.zxh.api.exchange.topic;

import com.rabbitmq.client.*;
import com.zxh.api.CommonUtils;

/**
 * @ClassName Consumer4TopicExchange
 * @Description exchange类型为topic的消费者
 * @Author xh.zhi
 * @Date 2019-10-25 13:46
 * @Version 1.0
 **/
public class Consumer4TopicExchange {

    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建管道channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、声明交换器
        String exchangeName = "test_topic_exchange";//交换器名称
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true,false,null);

        //5、声明队列
        String queueName = "test_topic_queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //6、队列绑定到交换器上
        String routingKey = "user.#";
        channel.queueBind(queueName,exchangeName,routingKey,null);

        //发现消息
        DeliverCallback deliverCallback = (consumerTag,delivery) ->{
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println("Consumer4TopicExchange consumerTag："+consumerTag+" 接收到信息："+message);
        };
        channel.basicConsume(queueName,true,deliverCallback,(consumerTag) ->{});
    }
}
