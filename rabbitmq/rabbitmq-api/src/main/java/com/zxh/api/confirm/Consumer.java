package com.zxh.api.confirm;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Consumer
 * @Description 消费者
 * @Author xh.zhi
 * @Date 2019-10-25 18:41
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
        String exchangeName = "test_confirm_exchange";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true,false,null);

        //5、什么队列
        String queueName = "test_confirm_queue";
        channel.queueDeclare(queueName,true,false,false,null);

        //6、队列绑定到交换器上
        String routingKey = "confirm.#";
        channel.queueBind(queueName,exchangeName,routingKey);

        //7、获取消息
        channel.basicConsume(queueName,true,((consumerTag, delivery) -> {
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println("接收到信息："+message);
           /* try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }),(consumerTag) ->{});
    }
}
