package com.lixiaozhuo._03_behavioral._09_mediator;

/**
 * 销售者
 */
public class Market implements Department {
    //持有中介者(总经理)的引用
	private Mediator m;
	
	public Market(Mediator m) {
		super();
		this.m = m;
		m.register("market", this);
	}

	@Override
	public void outAction() {
		System.out.println("汇报工作！项目承接的进度，需要资金支持！");
		m.command("financial");
	}

	@Override
	public void selfAction() {
		System.out.println("跑去接项目！");
	}

}
