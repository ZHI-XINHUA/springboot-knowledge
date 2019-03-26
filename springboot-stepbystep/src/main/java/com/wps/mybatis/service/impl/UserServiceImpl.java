package com.wps.mybatis.service.impl;


import com.wps.mybatis.dao.UserDao;
import com.wps.mybatis.entity.UserBO;
import com.wps.mybatis.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;


    @Override
    public UserBO getUserByUserCode(String userCode) {
        logger.info("UserServiceImpl>>>>>getUserByUserCode");
      return userDao.getUserByUserCode(userCode);
    }

    @Override
    public List<UserBO> getAllUser() {
        return userDao.getAllUser();
    }
}
