package com.lixiaozhuo._03_behavioral._01_template;

/**
 * 模板模式测试
 */
public class Client {
	public static void main(String[] args) {
		BankTemplateMethod btm = new DrawMoney();
		btm.process();
        System.out.println("====================================================================");
		//采用匿名内部类
		BankTemplateMethod btm2 = new BankTemplateMethod() {
			@Override
			public void transact() {
				System.out.println("我要存钱！");
			}
		};
		btm2.process();
        System.out.println("====================================================================");
		BankTemplateMethod btm3 = new BankTemplateMethod() {
			@Override
			public void transact() {
				System.out.println("我要理财！我这里有2000万韩币");
			}
		};
		
		btm3.process();
		
	}
}

/**
 * 实现模板
 */
class DrawMoney extends BankTemplateMethod {

	@Override
	public void transact() {
		System.out.println("我要取款！！！");
	}
	
}
