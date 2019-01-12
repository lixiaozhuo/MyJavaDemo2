package com.lixiaozhuo._01_creating._03_builder;

/**
 * 建筑者模式测试
 */
public class Client {
	public static void main(String[] args) {
		//组装飞船的工具
		AirShipDirector director = new CustomAirshipDirector(new CustomAirShipBuilder());
		//组装组件
		AirShip ship = director.directAirShip();

		System.out.println("发动机型号: " + ship.getEngine().getName());
		//发射
		ship.launch();
		
	}
}
