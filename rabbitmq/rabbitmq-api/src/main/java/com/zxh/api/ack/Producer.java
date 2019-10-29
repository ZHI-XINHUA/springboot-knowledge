package com.zxh.api.ack;

import com.rabbitmq.client.*;
import com.zxh.api.CommonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * ack 生产者
 */
public class Producer {
    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建Channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、发送消息
        String exchangeName="test_ack_exchange";
        String rountionKey = "ack.save";
        for(int i=1;i<=10;i++){
            Map<String,Object> header = new HashMap<>();
            header.put("num",i);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .headers(header)
                    .build();

            String msg = "Hello RabbitMQ ACK Message " + i;
            channel.basicPublish(exchangeName,rountionKey,true,properties,msg.getBytes());

        }


        //5、释放资源
        CommonUtils.releaseConnect(connection,channel);
    }
}
