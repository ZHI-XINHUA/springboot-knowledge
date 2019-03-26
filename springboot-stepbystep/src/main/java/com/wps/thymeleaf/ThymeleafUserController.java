package com.wps.thymeleaf;

import com.wps.mybatis.entity.UserBO;
import com.wps.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafUserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{usercode}")
    public String getUserByUserName(@PathVariable("usercode") String usercode, Model model){
        UserBO user = userService.getUserByUserCode(usercode);
        model.addAttribute("user",user);
        return  "user";
    }

    @GetMapping(value = "/user1/{usercode}")
    public void getUserByUserName1(@PathVariable("usercode") String usercode){
        UserBO user = userService.getUserByUserCode(usercode);
        System.out.println(user.getUserName());
       // return  user;
    }
}
