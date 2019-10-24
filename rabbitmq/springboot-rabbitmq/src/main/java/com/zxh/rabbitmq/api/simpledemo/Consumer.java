package com.zxh.rabbitmq.api.simpledemo;

import com.rabbitmq.client.*;

/**
 * @ClassName Consumer
 * @Description 消费者
 * @Author xh.zhi
 * @Date 2019-10-22 15:53
 * @Version 1.0
 **/
public class Consumer {
    private final static String QUEUQNAME="test001";

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

        //4、声明（创建）队列
        //参数：String queue（队列）, boolean durable(是否持久化), boolean exclusive（是否独占，如果独占则只能当前会话消费）,
        // boolean autoDelete（是否自动删除，服务器将在不再使用时删除它）,Map<String, Object> arguments（参数）
        channel.queueDeclare(QUEUQNAME,true,false,false,null);

        //5、接受消息（消费队列）consumerTag：消费者标志  delivery：消息
        DeliverCallback deliverCallback = (consumerTag,  delivery) ->{
            //delivery.getProperties() : Properties：是对消息进行修饰、比如消息的优先级、延迟等高级特性
            //delivery.getBody() ;//消息体内容
            BasicProperties properties = delivery.getProperties();

            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("接收到信息："+message);
        };

        //消费者被取消时的回调
        CancelCallback cancelCallback = consumerTag ->{
            System.out.println("cancelCallback '"+consumerTag+"'");
        };

        //6、设置获取消息的channel
        //参数：String queue（队列名称）, boolean autoAck（自动确认消息）, DeliverCallback deliverCallback(接收消息的回调), CancelCallback cancelCallback（消费者被取消时的回调）
        channel.basicConsume(QUEUQNAME,true,deliverCallback,cancelCallback);



        //如果不是一直消费，则关闭资源
        //connection.close();

    }
}
