package com.zxh.api.returnlinstener;

import com.rabbitmq.client.*;
import com.zxh.api.CommonUtils;

import java.io.IOException;

public class Producer {
    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建Channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、添加返回监听
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("---------handle  return----------");
                System.err.println("replyCode: " + replyCode);
                System.err.println("replyText: " + replyText);
                System.err.println("exchange: " + exchange);
                System.err.println("routingKey: " + routingKey);
                System.err.println("properties: " + properties);
                System.err.println("body: " + new String(body));
            }
        });

        //5、发送消息
        String exchangeName = "test_return_exchange";
        String routingKey = "return.save";
        String routingKeyError = "abc.save";
        String msg = "Hello RabbitMQ Return Message";
        channel.basicPublish(exchangeName, routingKeyError, true, null, msg.getBytes());


    }
}
