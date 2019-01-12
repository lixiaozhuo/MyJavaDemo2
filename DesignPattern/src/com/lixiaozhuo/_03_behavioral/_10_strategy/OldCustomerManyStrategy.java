package com.lixiaozhuo._03_behavioral._10_strategy;

/**
 * 老客户大量策略
 */
public class OldCustomerManyStrategy implements Strategy {

	@Override
	public double getPrice(double standardPrice) {
		System.out.println("打八折");
		return standardPrice*0.8;
	}

}
