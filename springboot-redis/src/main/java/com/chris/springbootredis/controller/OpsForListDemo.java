package com.chris.springbootredis.controller;

import com.chris.springbootredis.config.R;
import com.chris.springbootredis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: linfei
 * @date: 2019/07/23
 * @Description:
 */
@RestController
@Slf4j
public class OpsForListDemo {
    private RedisUtil redisUtil;

    @Autowired
    public OpsForListDemo(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
    /**
     * @Author: linfei
     * @Description:  redis list 添加
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @PostMapping("/redisLSet")
    public R redisLSet(String key , String value){
        redisUtil.lSet(key,value);
        return R.ok();
    }
    /**
     * @Author: linfei
     * @Description:  redis list 添加
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @PostMapping("/redisLSetTime")
    public R redisLSetTime(String key , String value,Long time){
        redisUtil.lSet(key,value,time);
        return R.ok();
    }

    /**
     * @Author: linfei
     * @Description:  redis list 获取 指定范围内的reids list集合
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @GetMapping("/redisLGet")
    public R redisLGet(String key , Long start ,Long end){
        List<Object> objects =  redisUtil.lGet(key,start,end);
        return R.ok().put("result",objects);
    }
    /**
     * @Author: linfei
     * @Description:  redis list 获取key对应list 的长度
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @GetMapping("/lGetListSize")
    public R lGetListSize(String key ){
        Long objects =  redisUtil.lGetListSize(key);
        return R.ok().put("result",objects);
    }
    /**
     * @Author: linfei
     * @Description:  redis list 获取key对应list 的长度
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @GetMapping("/lGetIndex")
    public R lGetIndex(String key ,Long index){
        Object objects =  redisUtil.lGetIndex(key,index);
        return R.ok().put("result",objects);
    }
    /**
     * @Author: linfei
     * @Description:  redis 删除
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @DeleteMapping("/redisLDelete")
    public R redisLDelete(String key ,Integer count,String value){
        Object objects =  redisUtil.lRemove(key,count,value);
        return R.ok().put("result",objects);
    }
    /**
     * @Author: linfei
     * @Description:  redis list 添加
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @PostMapping("/lUpdateIndex")
    public R lUpdateIndex(String key ,Long index, String value){
        redisUtil.lUpdateIndex(key,index,value);
        return R.ok();
    }
}
