package com.lixiaozhuo._01_creating._03_builder;

/**
 * 自定义飞船构件类
 */
public class CustomAirShipBuilder implements AirShipBuilder {
	@Override
	public Engine builderEngine() {
		System.out.println("构建发动机！");
		return new Engine("自定义发动机！");
	}

	@Override
	public EscapeTower builderEscapeTower() {
		System.out.println("构建逃逸塔");
		return new EscapeTower("自定义逃逸塔");
	}

	@Override
	public OrbitalModule builderOrbitalModule() {
		System.out.println("构建轨道舱");
		return new OrbitalModule("自定义轨道舱");
	}	
	
}
