package com.lixiaozhuo._03_behavioral._09_mediator;

/**
 * 中介者模式测试
 */
public class Client {
	public static void main(String[] args) {
		Mediator m = new President();
		

		Development development = new Development(m);
		Financial f = new Financial(m);

        Market   market = new Market(m);
		market.selfAction();
		market.outAction();
		
	}
}
