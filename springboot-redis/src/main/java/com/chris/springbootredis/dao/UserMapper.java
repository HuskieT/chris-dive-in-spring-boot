package com.chris.springbootredis.dao;

import com.chris.springbootredis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: chris
 * @Date: 2019/4/4 16:37
 * @Description:
 */
@Mapper
public interface UserMapper {

    List<User> getAll();

    User getById(Integer id);
}
