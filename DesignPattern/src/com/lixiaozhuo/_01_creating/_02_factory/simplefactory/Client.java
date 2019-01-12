package com.lixiaozhuo._01_creating._02_factory.simplefactory;

/**
 * 简单工厂测试
 */
public class Client {
	
	public static void main(String[] args) {
		//Car c1 = new Audi();
		//Car c2 = new Byd();
		Car c1 = CarFactory1.createCar("奥迪");
		Car c2 = CarFactory1.createCar("比亚迪");
		
		c1.run();
		c2.run();
	}
}
