package com.lixiaozhuo._01_creating._02_factory.abstractFactory;

/**
 * 座位
 */
public interface Seat {
	void massage();
}

/**
 * 好的座位
 */
class LuxurySeat implements Seat {

	@Override
	public void massage() {
		System.out.println("可以自动按摩！");
	}
	
}

/**
 * 次的座位
 */
class LowSeat implements Seat {

	@Override
	public void massage() {
		System.out.println("不能按摩！");
	}
	
}
