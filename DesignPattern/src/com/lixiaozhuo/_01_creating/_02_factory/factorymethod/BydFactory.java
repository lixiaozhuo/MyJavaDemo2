package com.lixiaozhuo._01_creating._02_factory.factorymethod;

/**
 * 比亚迪工厂类
 */
public class BydFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Byd();
	}

}
