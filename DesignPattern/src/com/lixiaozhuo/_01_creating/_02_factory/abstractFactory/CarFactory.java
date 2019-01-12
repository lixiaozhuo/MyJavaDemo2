package com.lixiaozhuo._01_creating._02_factory.abstractFactory;

/**
 * 汽车抽象工厂
 */
public interface CarFactory {
	Engine createEngine();
	Seat createSeat();
	Tyre createTyre();
}

