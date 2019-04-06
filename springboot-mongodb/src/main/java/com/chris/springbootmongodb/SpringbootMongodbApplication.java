package com.chris.springbootmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
//@EnableMongoRepositories(basePackages="com.chris")//当有些dao不在default page下时 可通过此方法进行注册扫描包
public class SpringbootMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run( SpringbootMongodbApplication.class, args );
    }

}
