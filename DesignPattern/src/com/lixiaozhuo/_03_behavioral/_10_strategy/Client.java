package com.lixiaozhuo._03_behavioral._10_strategy;

/**
 * 策略模式测试
 */
public class Client {
	public static void main(String[] args) {
		
		Strategy s1 = new OldCustomerManyStrategy();
		Context ctx = new Context(s1);
		
		ctx.getPrice(998);
		
	}
}
