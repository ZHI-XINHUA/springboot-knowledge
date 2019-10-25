package com.zxh.api.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Producer
 * @Description 消息生产者
 *           发送消息携带属性： AMQP.BasicProperties
 * @Author xh.zhi
 * @Date 2019-10-25 15:23
 * @Version 1.0
 **/
public class Producer {
    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建管道Channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、发送消息
        String exchange = "test_message_exchange";
        String routingKey = "test.message.send";

        //消息属性
        Map<String,Object> header = new HashMap<>();
        header.put("test","测试");
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                .contentType("text/plain")
                .contentEncoding("UTF-8")
                .expiration("2000") //过期时间
                .headers(header)
                .build();
        
        for (int i=0;i<10;i++){
            String message = " 编号"+i+"呼叫总部..... ";
            channel.basicPublish(exchange,routingKey,properties,message.getBytes());
            TimeUnit.SECONDS.sleep(1);
        }

        //5、释放资源
        CommonUtils.releaseConnect(connection,channel);


    }
}
