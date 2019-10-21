package com.kafka.springbootkafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName KafkaConsumerListener
 * @Description kafka消息监听器
 * @Author xh.zhi
 * @Date 2019-10-21 16:34
 * @Version 1.0
 **/
@Component
public class KafkaConsumerListener {

    @KafkaListener(topics = "${kafka.topic}")
    public void onMessage(String message){
        System.out.println("kafka 消费者监听器，接收信息："+message);
    }
}
