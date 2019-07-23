package com.chris.springbootredis.controller;

import com.chris.springbootredis.config.R;
import com.chris.springbootredis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author: linfei
 * @date: 2019/07/23
 * @Description: redis set 操作示例
 */
@RestController
@Slf4j
public class OpsForSetDemo {
    private RedisUtil redisUtil;

    public OpsForSetDemo(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
    /**
     * @Author: linfei
     * @Description:  redis set 添加
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @PostMapping("/redisSSet")
    public R redisSSet(String key , String value1,String value2){
        redisUtil.sSet(key,value1,value2);
        return R.ok();
    }

    /**
     * @Author: linfei
     * @Description:  redis set 添加  设置过期时间 （注 为整个key 设置过期时间）
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @PostMapping("/redisSSetTime")
    public R redisSSetTime(String key , String value,Long time){
        Long aLong = redisUtil.sSetAndTime(key,time,value);
        return R.ok().put("result",aLong);
    }

    /**
     * @Author: linfei
     * @Description:  redis set 获取key对应的set
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @GetMapping("/redisSGet")
    public R redisSGet(String key ){
        Set<Object> set = redisUtil.sGet(key);
        return R.ok().put("result",set);
    }

    /**
     * @Author: linfei
     * @Description:  redis set 添加
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @GetMapping("/redisSHasKey")
    public R redisSHasKey(String key,String value ){
        Boolean aBoolean = redisUtil.sHasKey(key,value);
        return R.ok().put("result",aBoolean);
    }
    /**
     * @Author: linfei
     * @Description:  redis set  删除指定key的 value值
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @DeleteMapping("/redisSSetReomve")
    public R redisSSetReomve(String key,String value ){
        Long count = redisUtil.setRemove(key,value);
        return R.ok().put("result",count);
    }


}
