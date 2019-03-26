package com.wps.jpa.controller;

import com.wps.jpa.entity.UserBO;
import com.wps.jpa.service.AnyUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jpa")
public class AnyUserServiceController {

    @Autowired
    private AnyUserService anyUserService;

    @RequestMapping("/save")
    public void save(){
        UserBO user = new UserBO();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName("李四");
        user.setUserCode("lisi");
        user.setPassword("xxvcvc");
        user.setEmail("dfdf@dd.com");
        //anyUserService.save(user);
        anyUserService.saveOrDelete(user);
    }

    @RequestMapping("/delete")
    public void delete(){
        anyUserService.delete("a");
    }

    @RequestMapping("/findAll")
    public void findAll(){
        List<UserBO> list = anyUserService.findAll();
        for (UserBO user: list
             ) {
            System.out.println(user.getUserId()+" "+user.getUserName()+" "+user.getPassword());

        }
    }

    @RequestMapping("/findById/{id}")
    public void finaById(@PathVariable("id") String id){
        UserBO user = anyUserService.findById(id);
        System.out.println(user.getUserId()+" "+user.getUserName()+" "+user.getPassword());
    }


}
