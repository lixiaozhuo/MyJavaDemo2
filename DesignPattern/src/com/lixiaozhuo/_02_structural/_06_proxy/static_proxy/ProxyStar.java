package com.lixiaozhuo._02_structural._06_proxy.static_proxy;

/**
 * 代理对象
 */
public class ProxyStar implements Star {
	
	private Star star;
	
	public ProxyStar(Star star) {
		super();
		this.star = star;
	}

	@Override
	public void bookTicket() {
		System.out.println("ProxyStar.bookTicket()");
	}

	@Override
	public void collectMoney() {
		System.out.println("ProxyStar.collectMoney()");
	}

	@Override
	public void confer() {
		System.out.println("ProxyStar.confer()");
	}

	@Override
	public void signContract() {
		System.out.println("ProxyStar.signContract()");
	}

	@Override
	public void sing() {
		star.sing();
	}

}
