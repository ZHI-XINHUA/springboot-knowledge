package com.wps.activemq.service.impl;

import com.wps.activemq.MyMoodProducer;
import com.wps.activemq.entity.AyMood;
import com.wps.activemq.repository.MyMoodRepository;
import com.wps.activemq.service.MyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class MyMoodServiceImpl implements MyMoodService {
    @Resource
    private MyMoodRepository myMoodRepository;

    @Autowired
    private MyMoodProducer myMoodProducer;

    @Override
    public AyMood save(AyMood ayMood) {
        return myMoodRepository.save(ayMood);
    }

    @Override
    public String asySave(AyMood ayMood) {
        //往队列 my.queue.asyn.save 推送消息，消息内容为说说实体
        ActiveMQQueue destination = new ActiveMQQueue("my.queue.asyn.save");
        myMoodProducer.sendMessage(destination,ayMood);
        return "success";
    }

    public List<AyMood> findAll(){
        long start = System.currentTimeMillis();
        System.out.println("开始任务....");
        List<AyMood> list = myMoodRepository.findAll();
        long end = System.currentTimeMillis();
        System.out.println("完成任务-->耗时："+(end-start));

        return list;
    }

    @Async //异步调用方法
    @Override
    public Future<List<AyMood>> asyFindAll() {
        long start = System.currentTimeMillis();
        System.out.println("异步开始任务....");
        List<AyMood> list = myMoodRepository.findAll();
        long end = System.currentTimeMillis();
        System.out.println("异步完成任务-->耗时："+(end-start));
        return new AsyncResult< List<AyMood>>(list);
    }


}
