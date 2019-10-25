package com.zxh.api.myconsumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

/**
 * @ClassName Producer
 * @Description 生产者
 * @Author xh.zhi
 * @Date 2019-10-25 17:51
 * @Version 1.0
 **/
public class Producer {

    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建管道channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、发送消息
        String exchangName = "test_myconsumer_exchange";
        String routingKey = "test.myconsumer.oa";
        String message = "myconsumer test!";
        channel.basicPublish(exchangName,routingKey,null,message.getBytes());

        //5、是否资源
        CommonUtils.releaseConnect(connection,channel);
    }
}
