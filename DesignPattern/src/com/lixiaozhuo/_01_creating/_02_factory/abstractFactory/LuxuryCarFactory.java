package com.lixiaozhuo._01_creating._02_factory.abstractFactory;

/**
 * 建造好的汽车
 */
public class LuxuryCarFactory implements CarFactory {

	@Override
	public Engine createEngine() {
		return new LuxuryEngine();
	}

	@Override
	public Seat createSeat() {
		return new LuxurySeat();
	}

	@Override
	public Tyre createTyre() {
		return new LuxuryTyre();
	}


}
