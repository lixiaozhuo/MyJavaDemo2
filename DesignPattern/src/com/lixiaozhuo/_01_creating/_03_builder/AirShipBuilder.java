package com.lixiaozhuo._01_creating._03_builder;

/**
 * 建造宇宙飞船
 */
public interface AirShipBuilder {
	/**
	 *建造发动机
	 * @return
	 */
	Engine builderEngine();

	/**
	 *建造轨道舱
	 * @return
	 */
	OrbitalModule builderOrbitalModule();

	/**
	 *建造逃逸塔
	 * @return
	 */
	EscapeTower  builderEscapeTower();
}
