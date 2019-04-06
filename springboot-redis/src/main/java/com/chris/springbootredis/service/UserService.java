package com.chris.springbootredis.service;

import com.chris.springbootredis.entity.User;

import java.util.List;

/**
 * @Auther: chris
 * @Date: 2019/4/4 16:38
 * @Description:
 */
public interface UserService {

    List<User> getAll();

    User getById(Integer id);
}
