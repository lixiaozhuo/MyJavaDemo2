package com.lixiaozhuo._01_creating._04_prototype;

import java.util.Date;

/**
 * 原型模式(深复制)
 *
 */
public class Client2 {
	public static void main(String[] args) throws CloneNotSupportedException {
		Date date = new Date(12312321331L);

		Sheep2 s1 = new Sheep2("少利",date);//原型
		Sheep2 s2 = (Sheep2) s1.clone();//克隆
		//原型
		System.out.println("原型: " + s1);
		System.out.println("原型: " + s1.getSName());
		date.setTime(23432432423L);
		System.out.println("原型: " + s1.getBirthday());
		//克隆
		s2.setSName("多利");
		System.out.println("克隆: " + s2);
		System.out.println("克隆: " + s2.getSName());
		System.out.println("克隆: " + s2.getBirthday());
		
		
		
	}
}
