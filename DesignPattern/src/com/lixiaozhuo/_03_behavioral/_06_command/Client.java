package com.lixiaozhuo._03_behavioral._06_command;

/**
 * 命令模式测试
 */
public class Client {
	public static void main(String[] args) {
		Command c = new ConcreteCommand(new Receiver());
		Invoke i = new Invoke(c);
		i.call();
	
		
//		new Receiver().action();
		
	}
}
