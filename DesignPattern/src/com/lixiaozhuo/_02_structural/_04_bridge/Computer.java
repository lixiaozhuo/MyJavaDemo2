package com.lixiaozhuo._02_structural._04_bridge;

/**
 * 电脑类型的维度
 */
public class Computer {
	
	protected Brand brand;
	
	public Computer(Brand b) {
		this.brand = b;
	}
	
	public void sale(){
		brand.sale();
	}
	
}
//台式机
class Desktop extends Computer {

	public Desktop(Brand b) {
		super(b);
	}
	
	@Override
	public void sale() {
		super.sale();
		System.out.println("销售台式机");
	}
}

//笔记本
class Laptop extends Computer {
	
	public Laptop(Brand b) {
		super(b);
	}
	
	@Override
	public void sale() {
		super.sale();
		System.out.println("销售笔记本");
	}
}
