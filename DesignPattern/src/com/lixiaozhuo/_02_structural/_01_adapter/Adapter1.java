package com.lixiaozhuo._02_structural._01_adapter;

/**
 * 适配器 (类适配器方式)
 *
 */
public class Adapter1 extends Adaptee implements Target {

	@Override
	public void handleReq() {
		super.request();
	}
	
}
