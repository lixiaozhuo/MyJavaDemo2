package com.lixiaozhuo._01_creating._02_factory.simplefactory;

/**
 * 简单工厂(通过一个方法建立对象)
 */
public class CarFactory1 {
	
	public static  Car createCar(String type){
		if("奥迪".equals(type)){
			return new Audi();
		}else if("比亚迪".equals(type)){
			return new Byd();
		}else{
			return null;
		}
	}
	
}
