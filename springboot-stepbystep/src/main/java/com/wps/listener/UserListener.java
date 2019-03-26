package com.wps.listener;


import com.wps.mybatis.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.wps.mybatis.entity.UserBO;

@WebListener
public class UserListener implements ServletContextListener {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserService userService;

    String key = "USER";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //获取用户
        UserBO userBO = userService.getUserByUserCode("zs");
        System.out.println("================="+userBO.getUserName());
        //清除缓存数据
      /*  redisTemplate.delete(key);

        //保存缓存数据
        redisTemplate.opsForValue().set(key,userBO);

        UserBO cacheObj = (UserBO)redisTemplate.opsForValue().get(key);
        System.out.println("================="+cacheObj.getUserName());*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
