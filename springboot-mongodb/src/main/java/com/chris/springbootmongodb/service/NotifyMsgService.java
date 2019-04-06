package com.chris.springbootmongodb.service;

import com.chris.springbootmongodb.entity.NotifyMsg;

import java.util.List;

/**
 * 接口服务
 * @author oKong
 *
 */
public interface NotifyMsgService {

    /**
     * 保存数据
     * @author 作者：oKong
     */
    NotifyMsg saveNotifyMsg(NotifyMsg msg);

    /**
     * 根据消息号查找
     * @author 作者：oKong
     */
    NotifyMsg findNotifyMsgByNo(String notifyNo);

    /**
     * 根据消息日期查找
     * @author 作者：oKong
     */
    List<NotifyMsg> findNotifyMsgByDate(String notifyDate);

    /**
     * 根据id进行删除 返回删除的对象
     * @author 作者：oKong
     */
    NotifyMsg delNotifyMsgById(String id);

}