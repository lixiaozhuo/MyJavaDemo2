package com.lixiaozhuo._01_creating._02_factory.abstractFactory;

/**
 * 抽象工厂测试
 */
public class Client {

	public static void main(String[] args) {
		CarFactory  factory = new LuxuryCarFactory();
		Engine e = factory.createEngine();
		e.run();
		e.start();
	}
}
