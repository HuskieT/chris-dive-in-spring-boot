package com.chris.springbootredis.controller;
import com.chris.springbootredis.entity.UserEntity;
import com.chris.springbootredis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
/**
 * @Auther: chris
 * @Date: 2019/4/4 11:22
 * @Description:
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    // redis中存储的过期时间60s
    private static int ExpireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key, String value){
        UserEntity userEntity =new UserEntity();
        userEntity.setId(Long.valueOf(1));
        userEntity.setGuid(String.valueOf(1));
        userEntity.setName("zhangsan");
        userEntity.setAge(String.valueOf(20));
        userEntity.setCreateTime(new Date());

        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }


}
