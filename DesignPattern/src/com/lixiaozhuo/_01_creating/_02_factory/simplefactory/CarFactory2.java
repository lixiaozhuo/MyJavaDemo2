package com.lixiaozhuo._01_creating._02_factory.simplefactory;

/**
 * 简单工厂(通过对应方法建立对象)
 *
 */
public class CarFactory2 {
	
	public static  Car createAudi(){
		return new Audi();
	}
	public static  Car createByd(){
		return new Byd();
	}
	
}
