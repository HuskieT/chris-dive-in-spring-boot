package com.chris.springbootmongodb;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author renlf
 * @Date 2017-08-02 14:23
 * @Description
 **/
public class DBOther {
    /**
     *@Author renlf
     *@Date 2017/8/2 14:26
     *@Description 获取指定日期
     *@Param
     *@Return
     *@Throws
     */
    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month - 1, day);
        return calendar.getTime();

    }
}
