package com.chris.springbootredis.controller;

import com.chris.springbootredis.config.R;
import com.chris.springbootredis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: linfei
 * @date: 2019/07/22
 * @Description: redis string类型 操作
 * */
@RestController
@Slf4j
public class OpsForValueDemo {

    private RedisUtil redisUtil;

    @Autowired
    public OpsForValueDemo(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
    /** 
     * @Author: linfei 
     * @Description:  redis字符串添加
     * @Param:  
     * @return:  
     * @Date: 2019/7/22 
     */
    @PostMapping("/stringInsert")
    public R  stringInsert(String key ,String value){
       redisUtil.set(key,value);
       return R.ok();
    }

    /**
     * @Author: linfei
     * @Description:  redis字符串添加 设置过期时间
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @PostMapping("/stringInsertTime")
    public R  stringInsertTime(String key ,String value,Integer time){
        redisUtil.set(key,value,time);
        return R.ok();
    }
    /**
     * @Author: linfei
     * @Description:  redis字符串添加 递增 递减
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @PostMapping("/stringInsertIncrement")
    public R  stringInsertIncrement(String key ,long count ,long delta){
        redisUtil.incr(key,count);
        String incrStr = redisUtil.get(key);
        log.info("redis streign key自增后结果："+incrStr);
        redisUtil.decr(key,delta);
        String decrStr = redisUtil.get(key);
        log.info("redis streign key自减后结果："+decrStr);
        return R.ok().put("incrStr",incrStr).put("decrStr",decrStr);
    }

    /**
     * @Author: linfei
     * @Description:  redis字符串删除
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @DeleteMapping("/stringDelete")
    public R  stringDelete(String key ){
        redisUtil.del(key);
        return R.ok();
    }

    /**
     * @Author: linfei
     * @Description:  获取key的值
     * @Param:
     * @return:
     * @Date: 2019/7/22
     */
    @GetMapping("/stringGet/{key}")
    public R  stringGet(@PathVariable("key")String key ){
       String result =  redisUtil.get(key);
       return R.ok().put("result",result);
    }
    /** 
     * @Author: linfei 
     * @Description: 验证key是否存在
     * @Param:  
     * @return:  
     * @Date: 2019/7/23 
     */
    @GetMapping("/hasKey/{key}")
    public R hasKey(@PathVariable("key")String key){
        Boolean result =  redisUtil.hasKey(key);
        return R.ok().put("result",result);
    }

    /**
     * @Author: linfei
     * @Description: 自定key的过期时间
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @PostMapping("/expireKey")
    public R expireKey(String key,int time){
        Boolean result =  redisUtil.expire(key,time);
        return R.ok().put("result",result);
    }
    /**
     * @Author: linfei
     * @Description: 自定key的过期时间
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @GetMapping("/getExpire/{key}")
    public R getExpire(@PathVariable("key")String key){
        Long result =  redisUtil.getExpire(key);
        return R.ok().put("endTime",result);
    }


}
