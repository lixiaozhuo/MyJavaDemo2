package com.lixiaozhuo._03_behavioral._10_strategy;

/**
 * 新客户少量策略
 */
public class NewCustomerFewStrategy implements Strategy {

	@Override
	public double getPrice(double standardPrice) {
		System.out.println("不打折，原价");
		return standardPrice;
	}

}
