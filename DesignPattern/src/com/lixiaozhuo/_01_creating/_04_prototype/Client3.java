package com.lixiaozhuo._01_creating._04_prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 原型模式(深复制,使用序列化和反序列化的方式实现深复制)
 *
 */
public class Client3 {
	public static void main(String[] args) throws CloneNotSupportedException, Exception {
		Date date = new Date(12312321331L);
		//原型
		Sheep1 s1 = new Sheep1("多利",date);
		System.out.println("原型: " + s1);
		System.out.println("原型: " + s1.getSName());
		System.out.println("原型: " + s1.getBirthday());
		//使用序列化和反序列化实现深复制
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream    oos = new ObjectOutputStream(bos);
		oos.writeObject(s1);
        //存放对象数据
		byte[] bytes = bos.toByteArray();
		ByteArrayInputStream  bis = new ByteArrayInputStream(bytes);
		ObjectInputStream	  ois = new ObjectInputStream(bis);
        //克隆好的对象！
		Sheep1 s2 = (Sheep1) ois.readObject();
		//修改原型数据
		date.setTime(new Date().getTime());
        System.out.println("原型: " + s1.getBirthday());
		//克隆
		s2.setSName("少利");
		System.out.println("克隆: " + s2);
		System.out.println("克隆: " + s2.getSName());
		System.out.println("克隆: " + s2.getBirthday());
	}
}
