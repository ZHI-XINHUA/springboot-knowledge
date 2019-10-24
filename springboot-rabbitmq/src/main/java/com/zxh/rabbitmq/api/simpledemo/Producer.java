package com.zxh.springbootrabbitmq.simpledemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Producer
 * @Description 生产者
 * @Author xh.zhi
 * @Date 2019-10-22 15:42
 * @Version 1.0
 **/
public class Producer {
    private final static String EXCHANGE = "";

    private final static String ROUNTING_KEY ="test001";

    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory 并配置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); //rabbitmq 地址
        factory.setPort(5672); //端口号
        factory.setVirtualHost("/"); //指定虚拟主机
        factory.setUsername("guest");
        factory.setPassword("guest");

        //2、获取连接
        Connection connection = factory.newConnection();

        //3、创建channel
        Channel channel = connection.createChannel();

        //4、通过channel发送数据
        for(int i=0;i<10;i++){
            String sendMsg = "Hello Rabbitmq"+i;
            //String exchange, String routingKey, BasicProperties props, byte[] body
            channel.basicPublish(EXCHANGE,ROUNTING_KEY,null,sendMsg.getBytes());

            //模拟耗时
            TimeUnit.SECONDS.sleep(1);
        }

        //5、关闭资源
        channel.close();
        connection.close();
    }
}
