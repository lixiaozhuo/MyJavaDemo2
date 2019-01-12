package com.lixiaozhuo._02_structural._01_adapter;

/**
 * 适配器 (对象适配器方式,使用了组合的方式跟被适配对象整合)
 *
 */
public class Adapter2  implements Target {
	
	private Adaptee adaptee;
	
	@Override
	public void handleReq() {
		adaptee.request();
	}

	public Adapter2(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
	
	
}
