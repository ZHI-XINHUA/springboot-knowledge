package com.wps.activemq;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 创建生产者
 */
@Service
public class MyMoodProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate; //发送消息的工具类

    public void sendMessage(ActiveMQQueue destinnation, Object message){
        jmsMessagingTemplate.convertAndSend(destinnation, message);
    }



}
