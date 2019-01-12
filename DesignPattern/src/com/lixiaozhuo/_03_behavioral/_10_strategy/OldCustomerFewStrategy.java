package com.lixiaozhuo._03_behavioral._10_strategy;

/**
 * 老顾客少量策略
 */
public class OldCustomerFewStrategy implements Strategy {

	@Override
	public double getPrice(double standardPrice) {
		System.out.println("打八五折");
		return standardPrice*0.85;
	}

}
