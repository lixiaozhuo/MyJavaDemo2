package com.lixiaozhuo._03_behavioral._07_state;

/**
 * 状态模式测试
 */
public class Client {
	public static void main(String[] args) {
		HomeContext ctx = new HomeContext();
		
		ctx.setState(new FreeState());
		ctx.setState(new BookedState());
	}
}
