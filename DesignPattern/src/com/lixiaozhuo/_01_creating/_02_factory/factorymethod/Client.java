package com.lixiaozhuo._01_creating._02_factory.factorymethod;

/**
 * 工厂模式测试
 */
public class Client {
	public static void main(String[] args) {
		Car c1 = new AudiFactory().createCar();
		Car c2 = new BydFactory().createCar();
		
		c1.run();
		c2.run();
	}
}
