package com.lixiaozhuo._01_creating._02_factory.abstractFactory;

/**
 * 建造次的汽车
 */
public class LowCarFactory implements CarFactory {

	@Override
	public Engine createEngine() {
		return new LowEngine();
	}

	@Override
	public Seat createSeat() {
		return new LowSeat();
	}

	@Override
	public Tyre createTyre() {
		return new LowTyre();
	}


}
