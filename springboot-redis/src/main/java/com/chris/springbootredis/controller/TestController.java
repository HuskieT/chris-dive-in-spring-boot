package com.chris.springbootredis.controller;

import com.chris.springbootredis.entity.User;
import com.chris.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: chris
 * @Date: 2019/4/4 16:34
 * @Description:
 */
@RestController
@RequestMapping("test")
public class TestController {


    private final UserService userService;

    @Autowired
    public TestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("all")
    public String all(){
       List<User> list =  userService.getAll();
       return list.toString();
    }

    @RequestMapping("user")
    public String user(){
        User user =  new User();
        user = userService.getById(1);
        return user.toString();
    }


}
