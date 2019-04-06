package com.chris.springbootredis.service.impl;

import com.chris.springbootredis.dao.UserMapper;
import com.chris.springbootredis.entity.User;
import com.chris.springbootredis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: chris
 * @Date: 2019/4/4 16:40
 * @Description:
 */
@Service(value = "userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    @Cacheable(value = "userList", keyGenerator = "cacheKeyGenerator")
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    @Cacheable(value = "user", keyGenerator = "cacheKeyGenerator")
    public User getById(Integer id) {
        return userMapper.getById(id);
    }
}
