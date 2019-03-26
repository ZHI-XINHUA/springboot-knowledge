package com.wps.activemq.service;

import com.wps.activemq.entity.AyMood;

import java.util.List;
import java.util.concurrent.Future;

public interface MyMoodService {
    AyMood save(AyMood ayMood);

    String asySave(AyMood ayMood);

    List<AyMood> findAll();

    /**
     * 异步查询
     * @return
     */
    Future<List<AyMood>> asyFindAll();
}
