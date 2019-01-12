package com.lixiaozhuo._03_behavioral._09_mediator;

/**
 * 中介对象接口
 */
public interface Mediator {
	
	void register(String dName, Department d);
	
	void command(String dName);
	
}
