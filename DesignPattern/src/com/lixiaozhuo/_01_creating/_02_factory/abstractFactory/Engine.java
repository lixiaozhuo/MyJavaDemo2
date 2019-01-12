package com.lixiaozhuo._01_creating._02_factory.abstractFactory;

/**
 *发动机
 */
public interface Engine {
	void run();
	void start();
}

/**
 *好的发动机
 */
class LuxuryEngine implements Engine{

	@Override
	public void run() {
		System.out.println("转的快！");
	}

	@Override
	public void start() {
		System.out.println("启动快!可以自动启停！");
	}
	
}

/**
 * 次的发动起
 */
class LowEngine implements Engine{
	
	@Override
	public void run() {
		System.out.println("转的慢！");
	}
	
	@Override
	public void start() {
		System.out.println("启动慢!");
	}
	
}