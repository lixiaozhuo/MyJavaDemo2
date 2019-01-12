package com.lixiaozhuo._03_behavioral._10_strategy;

/**
 * 新客户大量策略
 */
public class NewCustomerManyStrategy implements Strategy {

	@Override
	public double getPrice(double standardPrice) {
		System.out.println("打九折");
		return standardPrice*0.9;
	}

}
