package com.wps;

import com.wps.activemq.MyMoodProducer;
import com.wps.activemq.entity.AyMood;
import com.wps.activemq.service.MyMoodService;
import com.wps.redis.RedisService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private RedisService redisService;

    @Autowired
    private MyMoodService myMoodService;

    @Autowired
    private MyMoodProducer myMoodProducer;

    @org.junit.Test
    public void redisTest(){
        redisService.testApi();
    }

    @org.junit.Test
    public void testMood(){
        AyMood ayMood = new AyMood();
        ayMood.setId(UUID.randomUUID().toString());
        ayMood.setContent("今天天气很好");
        ayMood.setUserId("e98b0351-9fa9-4de7-92e9-cf5c79c663ef");
        ayMood.setPraiseNum(101);
        ayMood.setPublishTime(new Date(System.currentTimeMillis()));
        myMoodService.save(ayMood);
    }

    @org.junit.Test
    public void sendMesg(){
        ActiveMQQueue destinnation = new ActiveMQQueue("mymood.queue");
        myMoodProducer.sendMessage(destinnation,"hello activemq");
    }

    @org.junit.Test
    public void asySave(){
        AyMood ayMood = new AyMood();
        ayMood.setId(UUID.randomUUID().toString());
        ayMood.setContent("卧槽，坑爹");
        ayMood.setUserId("e98b0351-9fa9-4de7-92e9-cf5c79c663ef");
        ayMood.setPraiseNum(99);
        ayMood.setPublishTime(new Date(System.currentTimeMillis()));
        myMoodService.asySave(ayMood);
    }

    @org.junit.Test
    public void findAll(){
        System.out.println("=========== 同步方法 ==============");
        System.out.println("======第一次调用=====");
        myMoodService.findAll();
        System.out.println("======第二次调用=====");
        myMoodService.findAll();
        System.out.println("======第三次调用=====");
        myMoodService.findAll();

        System.out.println();
        System.out.println("=========== 异步方法 ==============");
        System.out.println("======第一次调用=====");
        myMoodService.asyFindAll();
        System.out.println("======第二次调用=====");
        myMoodService.asyFindAll();
        System.out.println("======第三次调用=====");
        myMoodService.asyFindAll();
    }
}
