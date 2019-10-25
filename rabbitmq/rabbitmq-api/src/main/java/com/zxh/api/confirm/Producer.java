package com.zxh.api.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zxh.api.CommonUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Producer
 * @Description 生产者
 *      消息确认
 * @Author xh.zhi
 * @Date 2019-10-25 18:36
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

        //4、设置消息投递模式: 消息的确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.add";
        //5、发送消息
        for(int i=1;i<=10;i++){
            String message = " 编号"+i+"呼叫总部..... ";
            channel.basicPublish(exchangeName,routingKey,null,message.getBytes());
            System.out.println("发送第"+i+"条消息");
        }

        //6、添加确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-------ack!-----------");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-------no ack!-----------");
            }
        });

        //7、释放资源
       // CommonUtils.releaseConnect(connection,channel);
    }
}
