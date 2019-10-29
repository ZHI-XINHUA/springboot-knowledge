package com.zxh.api.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

public class Producer {
    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、发送消息
        String exchange = "test_dlx_exchange";
        String routingKey = "dlx.save";
        for (int i=1;i<=10;i++){
            AMQP.BasicProperties properties = new AMQP.BasicProperties()
                    .builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .expiration("10000")
                    .build();
            String msg = "Hello RabbitMQ DLX Message:"+i;
            channel.basicPublish(exchange,routingKey,properties,msg.getBytes());
        }

        //释放资源
        CommonUtils.releaseConnect(connection,channel);
    }
}
