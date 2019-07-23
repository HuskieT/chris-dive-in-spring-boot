package com.chris.springbootredis.controller;

import com.chris.springbootredis.config.R;
import com.chris.springbootredis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: linfei
 * @date: 2019/07/23
 * @Description: redis hash 示例调用
 */
@RestController
@Slf4j
public class OpsForHashDemo {
    private RedisUtil redisUtil;

    @Autowired
    public OpsForHashDemo(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * @Author: linfei
     * @Description:  redis hash get获取
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @GetMapping("/hashGet")
    public R hashGet(String key,String item){
        Object result =  redisUtil.hget(key,item);
        return R.ok().put("result",result);
    }
    /**
     * @Author: linfei
     * @Description:  redis hash 返回hash key 对应所有的field和value
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @GetMapping("/hashMGet/{key}")
    public R hashMGet(@PathVariable("key")String key){
        Object result =  redisUtil.hmget(key);
        return R.ok().put("result",result);
    }

    /**
     * @Author: linfei
     * @Description:  redis hash set 一组key value
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @PostMapping("/hashSet")
    public R hashSet(String key,String item,String value){
        redisUtil.hset(key,item,value);
        return R.ok();
    }
    /**
     * @Author: linfei
     * @Description:  redis hash set 批量设置hash key
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @PostMapping("/hashMSet")
    public R hashMSet(String key){
        Map<String,Object> map = new HashMap<>();
        map.put("name","小芒果");
        map.put("age",12);
        map.put("price",12.23D);
        map.put("id",123L);
        redisUtil.hmset(key,map);
        return R.ok();
    }

    /**
     * @Author: linfei
     * @Description:  redis hash set 批量设置hash key 并设置过期时间
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @PostMapping("/hashMSetTime")
    public R hashMSetTime(String key,int time){
        Map<String,Object> map = new HashMap<>();
        map.put("name","小芒果");
        map.put("age",12);
        map.put("id",123L);
        redisUtil.hmset(key,map,time);
        return R.ok();
    }

    /**
     * @Author: linfei
     * @Description:  redis hash set 批量设置hash key 并设置过期时间
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @DeleteMapping("/hashDelete")
    public R hashDelete(String key,String item){
        redisUtil.hdel(key,item);
        return R.ok();
    }
    /**
     * @Author: linfei
     * @Description:  redis hash 删除key对应的所有值
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @DeleteMapping("/hashMDelete")
    public R hashMDelete(String key){
        redisUtil.hdel(key,null);
        return R.ok();
    }
    /**
     * @Author: linfei
     * @Description:  redis hash 是否有key item 对应的值
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @GetMapping("/hashHasKey")
    public R hashHasKey(String key,String item){
       Boolean aBoolean =  redisUtil.hHasKey(key,item);
        return R.ok().put("result",aBoolean);
    }

    /**
     * @Author: linfei
     * @Description:  redis hash 自增
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @PostMapping("/hashIncrKey")
    public R hashIncrKey(String key, String item,Integer by){
        Long aLong =  redisUtil.hincr(key,item,by);
        return R.ok().put("result",aLong);
    }
    /**
     * @Author: linfei
     * @Description:  redis hash  自减
     * @Param:
     * @return:
     * @Date: 2019/7/23
     */
    @PostMapping("/hashDecrKey")
    public R hashDecrKey(String key, String item,Integer by){
        Long aLong =  redisUtil.hdecr(key,item,by);
        return R.ok().put("result",aLong);
    }

}
