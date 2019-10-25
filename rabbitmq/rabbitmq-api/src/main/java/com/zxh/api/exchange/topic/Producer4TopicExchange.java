package com.zxh.api.exchange.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

/**
 * @ClassName Producer4TopicExchange
 * @Description exhange类型为topic
 *    路由规则：topic类型的交换器规则与direct类型的相似（完全匹配），topic消息路由是根据BindingKey和RountingKey模糊匹配
 *      RountingKey和RountingKey为一个点号“.”分隔的字符串（被点号隔开的每一段独立的字符串成为一个单词） 如com.rabbitmq.clent
 *      BindingKey可以存在两种字符串“*”和“#” 用于模糊匹配
 *         “#”：表示匹配一个单词
 *         “*”：表示匹配多个单词（可以是零个）
 * @Author xh.zhi
 * @Date 2019-10-25 9:46
 * @Version 1.0
 **/
public class Producer4TopicExchange {
    public static void main(String[] args) throws Exception{
        //1、创建连接工厂ConnectionFactory
        ConnectionFactory connectionFactory = CommonUtils.getConnectionFactory();

        //2、创建连接Connection
        Connection connection = CommonUtils.getConnection(connectionFactory);

        //3、创建管道channel
        Channel channel = CommonUtils.getChannel(connection);

        //4、发送消息
        String exchange = "test_topic_exchange";//交换机名称


        //设置消息属性
        AMQP.BasicProperties properties = new AMQP.BasicProperties();
        String routingKey1 = "user.save";
        String routingKey2 = "user.update";
        String routingKey3 = "user.delete.abc";

        String message = "Hello World RabbitMQ 4 TOPIC Exchange Message >>> ";//消息内容

        channel.basicPublish(exchange,routingKey1,properties,(message+routingKey1).getBytes());
        channel.basicPublish(exchange,routingKey2,properties,(message+routingKey2).getBytes());
        channel.basicPublish(exchange,routingKey3,properties,(message+routingKey3).getBytes());



        //释放资源
        CommonUtils.releaseConnect(connection,channel);
    }
}
