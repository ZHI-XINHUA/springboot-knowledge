package com.wps.activemq;

import com.wps.activemq.entity.AyMood;
import com.wps.activemq.service.MyMoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * 创建消费者
 */
@Service
public class MyMoodConsumer {
    @Autowired
    private MyMoodService myMoodService;

    @JmsListener(destination = "mymood.queue") //@JmsListener 使用 JmsListener 配直消费者监听的 mymood.queue，其中 text 是才妾收到的消息 。
    public void receviewMsg(String msg){
        System.out.println("接受信息："+msg);
    }



    @JmsListener(destination = "my.queue.asyn.save")
    public void receviewQueuq(AyMood ayMood){
        System.out.println("接收到要保存的说活");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //保存数据库
        myMoodService.save(ayMood);
    }
}
