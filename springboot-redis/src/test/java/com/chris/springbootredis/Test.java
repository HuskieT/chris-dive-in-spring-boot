package com.chris.springbootredis;

import java.util.Random;

/**
 * @author: linfei
 * @date: 2019/07/23
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        int a =  new Random().nextInt(900000)+100000;
        String re=String.format("%06d", 133);
        System.out.println(re);
    }
}
