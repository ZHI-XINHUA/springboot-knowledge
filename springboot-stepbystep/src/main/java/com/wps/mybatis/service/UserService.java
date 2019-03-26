package com.wps.mybatis.service;


import com.wps.mybatis.entity.UserBO;

import java.util.List;

public interface UserService {
    UserBO getUserByUserCode(String userCode);

    List<UserBO> getAllUser();
}
