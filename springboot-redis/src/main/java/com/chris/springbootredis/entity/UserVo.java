package com.chris.springbootredis.entity;

import lombok.*;

/**
 * @Auther: chris
 * @Date: 2019/4/4 12:40
 * @Description:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor //无参构造方法
@AllArgsConstructor // 全参构造方法
public class UserVo {
    public  static final String Table = "t_user";

    private String name;
    private String address;
    private Integer age;
}
