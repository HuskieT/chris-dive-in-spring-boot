package com.chris.springbootredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.chris")
@MapperScan("com.chris.springbootredis.dao")
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run( RedisApplication.class, args);
    }

}
