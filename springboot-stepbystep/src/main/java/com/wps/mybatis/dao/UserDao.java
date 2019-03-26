package com.wps.mybatis.dao;


import com.wps.mybatis.entity.UserBO;

import java.util.List;

//@Mapper  如果启动类添加了@MapperScan扫描，这里就不用添加@Mapper了
public interface UserDao {
   // UserBO getUserByUserCode(@Param("usercode") String usercode);
    UserBO getUserByUserCode(String usercode);

    List<UserBO> getAllUser();
}
