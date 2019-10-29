package com.zxh.api.limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

public class Producer {
    public static void main(String[] args) throws Exception {
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、发送消息
        String exchangeName="test_limit_exchange";
        String rountionKey = "limit.save";
        for (int i=1;i<10;i++){
            String msg = "Hello RabbitMQ QOS Message "+i;
            channel.basicPublish(exchangeName,rountionKey,true,null,msg.getBytes());
        }

        //5、关闭资源
        CommonUtils.releaseConnect(connection,channel);
    }
}
