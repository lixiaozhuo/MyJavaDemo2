package com.lixiaozhuo._03_behavioral._07_state;

/**
 * 空闲状态
 *
 */
public class FreeState implements State {

	@Override
	public void handle() {
		System.out.println("房间空闲！！！没人住！");
	}

}
