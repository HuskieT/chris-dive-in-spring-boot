package com.chris.springbootredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: linfei
 * @date: 2019/07/15
 * @Description:
 */
@Component
public class RedisTestService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void saveCXDataTORedis(){
        redisTemplate.opsForZSet().add("cx_score_20190712","123456", 43.22);
    }
}
