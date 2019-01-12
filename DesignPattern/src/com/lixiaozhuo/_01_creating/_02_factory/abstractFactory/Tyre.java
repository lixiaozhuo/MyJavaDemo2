package com.lixiaozhuo._01_creating._02_factory.abstractFactory;

/**
 * 轮胎
 */
public interface Tyre {
	void revolve();
}

/**
 * 好的轮胎
 */
class LuxuryTyre implements Tyre {

	@Override
	public void revolve() {
		System.out.println("旋转不磨损！");
	}
	
}

/**
 * 次的轮胎
 */
class LowTyre implements Tyre {

	@Override
	public void revolve() {
		System.out.println("旋转磨损快！");
	}
	
}