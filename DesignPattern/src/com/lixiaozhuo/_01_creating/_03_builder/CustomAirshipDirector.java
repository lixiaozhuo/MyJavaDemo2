package com.lixiaozhuo._01_creating._03_builder;

/**
 * 自定义飞船组装类
 */
public class CustomAirshipDirector implements AirShipDirector {
	//飞船创建工具
	private AirShipBuilder builder;

	public CustomAirshipDirector(AirShipBuilder builder) {
		this.builder = builder;
	}


	@Override
	public AirShip directAirShip() {
		//创建飞船组件
		Engine e = builder.builderEngine();
		OrbitalModule o = builder.builderOrbitalModule();
		EscapeTower et = builder.builderEscapeTower();
		//装配成飞船对象
		AirShip ship = new AirShip();
		ship.setEngine(e);
		ship.setEscapeTower(et);
		ship.setOrbitalModule(o);
		//返回飞船对象
		return ship;
	}

}
