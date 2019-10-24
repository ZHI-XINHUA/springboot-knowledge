package com.zxh.api.exchange.fanout;

import com.rabbitmq.client.*;
import com.zxh.api.CommonUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName Consumer4FanoutExchange
 * @Description 消费者
 * @Author xh.zhi
 * @Date 2019-10-24 15:47
 * @Version 1.0
 **/
public class Consumer4FanoutExchange {

    public static void main(String[] args) throws IOException {
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();
        //2、创建Connection连接
        Connection connection = CommonUtils.getConnection(connectionFactory);
        //3、创建channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、声明exchange
        //String exchange, BuiltinExchangeType type, boolean durable, boolean autoDelete,Map<String, Object> arguments
        String exchange = "test_fanout_exchange";//exchange名称
        BuiltinExchangeType type = BuiltinExchangeType.FANOUT;// exchange类型，fanout
        boolean durable = true;//是否持久化
        boolean autoDelete = false;//true:exchange不再使用时自动删除
        Map<String, Object> arguments = null;//参数
        String exchangeType = "fanout";
        channel.exchangeDeclare(exchange,exchangeType,durable,autoDelete,arguments);

        //5、声明消费队列
        //String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments
        String queue = "test_fanout_queue";//队列名称
        channel.queueDeclare(queue,false,false,false,null);

        //6、queue绑定到交换器中
        //String queue, String exchange, String routingKey
        String routingKey = "";	//不设置路由键
        channel.queueBind(queue,exchange,routingKey);

        //7、接受消息（消费队列）consumerTag：消费者标志  delivery：消息
        DeliverCallback deliverCallback = (consumerTag, delivery) ->{
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

        //8、设置获取消息的channel
        //参数：String queue（队列名称）, boolean autoAck（自动确认消息）, DeliverCallback deliverCallback(接收消息的回调), CancelCallback cancelCallback（消费者被取消时的回调）
        channel.basicConsume(queue,true,deliverCallback,cancelCallback);

    }
}
