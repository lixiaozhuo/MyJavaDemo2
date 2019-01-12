package com.lixiaozhuo._01_creating._04_prototype;

import java.util.Date;

/**
 * 测试原型模式(浅克隆)
 */
public class Client1 {
    public static void main(String[] args) throws Exception {

        Sheep1 s1 = new Sheep1("少利", new Date(12312321331L));//原型
        Sheep1 s2 = (Sheep1) s1.clone();//克隆
        //原型
        System.out.println("原型: " + s1);
        System.out.println("原型: " + s1.getSName());
        System.out.println("原型: " + s1.getBirthday());
        //克隆
        s2.setSName("多利");
        System.out.println("克隆: " + s2);
        System.out.println("克隆: " + s2.getSName());
        System.out.println("克隆: " + s2.getBirthday());

    }
}
