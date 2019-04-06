package com.chris.springbootmongodb.controller;

import com.chris.springbootmongodb.entity.NotifyMsg;
import com.chris.springbootmongodb.service.NotifyMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * mongoTemplate 示例
 * @author oKong
 *
 */
@RestController
@RequestMapping("/template")
@Slf4j
@Api(tags="用户API")
public class MongoTemplateController {

    @Autowired
    NotifyMsgService notifyMsgService;
    @ApiOperation(value="mongoTemplate方式新增")
    @PostMapping("/add")
    public NotifyMsg add(NotifyMsg msg) {
        log.info("mongoTemplate方式新增：{}", msg);
        return notifyMsgService.saveNotifyMsg(msg);
    }

    @ApiOperation(value="mongoTemplate方式删除")
    @ApiImplicitParam(name="id",value="删除ID",required=true)
    @PostMapping("del/{id}")
    public NotifyMsg del(@PathVariable String id) {
        log.info("mongoTemplate方式删除：{}", id);
        return notifyMsgService.delNotifyMsgById(id);
    }

    @ApiOperation(value="mongoTemplate方式查找：notifyNo-{}")
    @ApiImplicitParam(name="no",value="查询ID",required=true)
    @GetMapping("/find/{no}")
    public NotifyMsg findNotifyMsgByNo(@PathVariable String no){
        log.info("mongoTemplate方式查找：notifyNo-{}", no);
        return notifyMsgService.findNotifyMsgByNo(no);
    }

    @ApiOperation(value="mongoTemplate方式查找：notifyDate-{}")
    @GetMapping("/find/list/{date}")
    public List<NotifyMsg> findNotifyMsgByDate(@PathVariable String date){
        log.info("mongoTemplate方式查找：notifyDate-{}", date);
        return notifyMsgService.findNotifyMsgByDate(date);
    }
}