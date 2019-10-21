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

    //kafkaTemplate
    private final KafkaTemplate<String,String> kafkaTemplate;

    private final String topic;

    @Autowired
    public KafkaProducerController(KafkaTemplate kafkaTemplate,@Value("${kafka.topic}") String topic){
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @GetMapping("/message/send")
    @ResponseBody
    public String sendMsg(@RequestParam String msg){
        //ProducerRecord producerRecord = new ProducerRecord(topic,partition,timestamp,key,value);
       // ProducerRecord producerRecord = new ProducerRecord(topic,msg);
       // ListenableFuture<SendResult<String, String>> result =kafkaTemplate.send(producerRecord);
        kafkaTemplate.send(topic,msg);
        return "success";
    }
}
