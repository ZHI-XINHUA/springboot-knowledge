package com.zxh.api;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName CommonUtils
 * @Description TODO
 * @Author xh.zhi
 * @Date 2019-10-24 15:33
 * @Version 1.0
 **/
public class CommonUtils {
    //rabbitmq连接地址
    private static  final String HOST = "localhost";
    //端口号
    private static final int PORT = 5672;
    //指定的虚拟主机
    private static final String VIRTUAL_HOST = "/";
    //用户名
    private static final String USER_NAME = "guest";
    //密码
    private static final String PASSWORD = "guest";

    /**
     * 创建ConnectionFactory
     * @return
     */
    public static ConnectionFactory getConnectionFactory(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST); //rabbitmq 地址
        factory.setPort(PORT); //端口号
        factory.setVirtualHost(VIRTUAL_HOST); //指定虚拟主机
        factory.setUsername(USER_NAME);
        factory.setPassword(PASSWORD);
        return factory;
    }

    /**
     * 创建连接
     * @return
     */
    public static Connection getConnection(ConnectionFactory connectionFactory){
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建管道 channel
     * @return
     */
    public static Channel getChannel(Connection connection){
        try {
            return connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void releaseConnect(Connection connection,Channel channel){
        try {
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
