package com.zxh.api.exchange.direct;

import com.rabbitmq.client.*;
import com.zxh.api.CommonUtils;

/**
 * @ClassName Consumer4DirectExchange
 * @Description exchange类型为direct的消费者
 * @Author xh.zhi
 * @Date 2019-10-25 9:11
 * @Version 1.0
 **/
public class Consumer4DirectExchange {
    public static void main(String[] args) throws Exception{
        //1、创建连接工厂ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建连接Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建管道channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、声明交换器
        String exchangeName = "test_direct_exchange";//交换器名称
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,true,false,null);

        //5、声明消费队列
        String queueName = "test_direct_queuq";//队列名称
        channel.queueDeclare(queueName,true,false,false,null);

        //6、队列绑定到交换器中
        String routingKey = "test_direct_key";//绑定路由key
        channel.queueBind(queueName,exchangeName,routingKey);

        //接受消息（消费队列）consumerTag：消费者标志  delivery：消息
        DeliverCallback deliverCallback = (consumerTay,delivery) ->{
            //AMQP.BasicProperties properties = delivery.getProperties();
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println("Consumer4FanoutExchange 接收到信息："+message);

        };
        // 获取队列消息
        channel.basicConsume(queueName,true,deliverCallback,consumerTag -> {});

    }
}
