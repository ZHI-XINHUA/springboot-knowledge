package com.wps.mybatis;

import com.wps.mybatis.entity.UserBO;
import com.wps.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{usercode}")
    public UserBO getUserByUserName(@PathVariable("usercode") String usercode){
        return  userService.getUserByUserCode(usercode);
    }

    @GetMapping(value = "/user")
    public List<UserBO> getAllUser(){
        return  userService.getAllUser();
    }
}
