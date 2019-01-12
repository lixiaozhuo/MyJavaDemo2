package com.lixiaozhuo._01_creating._02_factory.factorymethod;

/**
 * 奔驰工厂
 */
public class BenzFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Benz();
	}

}
