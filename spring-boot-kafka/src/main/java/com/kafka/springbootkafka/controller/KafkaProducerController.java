package com.kafka.springbootkafka.controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName KafkaProducerController
 * @Description kafka生产者
 * @Author xh.zhi
 * @Date 2019-10-18 12:18
 * @Version 1.0
 **/
@RestController
@RequestMapping("/producer")

public class KafkaProducerController {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerController.class);

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public KafkaProducerController(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send")
    @ResponseBody
    public String sendMsg(@Value("{topic}") String topic ,@RequestParam String msg){
        System.out.println(topic);
        Integer partition = 0;
        //ProducerRecord producerRecord = new ProducerRecord(topic,partition,timestamp,key,value);
       // ProducerRecord producerRecord = new ProducerRecord(topic,msg);
       // ListenableFuture<SendResult<String, String>> result =kafkaTemplate.send(producerRecord);
        return "success";
    }
}
