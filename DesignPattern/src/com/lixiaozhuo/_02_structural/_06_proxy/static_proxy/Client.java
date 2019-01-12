package com.lixiaozhuo._02_structural._06_proxy.static_proxy;

/**
 * 静态代理测试
 */
public class Client {
	public static void main(String[] args) {
	    //真实对象
		Star real = new RealStar();
		//代理对象
		Star proxy = new ProxyStar(real);
		
		proxy.confer();
		proxy.signContract();
        proxy.collectMoney();
		proxy.bookTicket();

		proxy.sing();
	}
}
