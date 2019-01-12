package com.lixiaozhuo._03_behavioral._01_template;

/**
 * 模板方法
 */
public abstract class BankTemplateMethod {
	//具体方法
	private void takeNumber(){
		System.out.println("取号排队");
	}
    //办理具体的业务(钩子方法)
	public abstract void transact();

    private void evaluate(){
		System.out.println("反馈评分");
	}


    //模板方法！！！
	public final void process(){
		this.takeNumber();

		this.transact();

		this.evaluate();
	}
	
}
