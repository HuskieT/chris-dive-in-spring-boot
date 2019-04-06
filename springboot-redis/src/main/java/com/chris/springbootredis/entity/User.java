package com.chris.springbootredis.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.security.PrivateKey;

/**
 * @Auther: chris
 * @Date: 2019/4/4 16:36
 * @Description: 数据库user表
 */
@Getter
@Setter
@ToString
@NoArgsConstructor //无参构造方法
@AllArgsConstructor // 全参构造方法
@Alias("user")
public class User implements Serializable {
    private static final long serialVersionUID = 8655851615465363473L;
    private  Integer id;
    private  String  name;
    private  Integer age;

}
