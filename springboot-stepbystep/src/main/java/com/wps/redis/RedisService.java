package com.wps.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void testApi(){
        //新增
        redisTemplate.opsForValue().set("name","zxh");
        //查询
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println("name="+name);

        //删除
        redisTemplate.delete("name");

        //更新
        redisTemplate.opsForValue().set("name","zxh123");

        stringRedisTemplate.opsForValue().set("name","sdfdfdfs");
        //查询
        name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name="+name);



    }
}
