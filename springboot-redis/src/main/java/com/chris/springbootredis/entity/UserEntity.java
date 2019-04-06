package com.chris.springbootredis.entity;

import lombok.*;

import java.util.Date;

/**
 * @Auther: chris
 * @Date: 2019/4/4 11:22
 * @Description:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor //无参构造方法
@AllArgsConstructor // 全参构造方法
public class UserEntity {
    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;
}
