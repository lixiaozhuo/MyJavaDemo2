package com.lixiaozhuo._03_behavioral._02_oberserver._01_custom;

/**
 * 观察者实体
 */
public class ObserverA implements Observer {
    //myState需要跟目标对象的state值保持一致！
	private int myState;
	
	
	@Override
	public void update(Subject subject) {
		myState = ((ConcreteSubject)subject).getState();
	}


	public int getMyState() {
		return myState;
	}
	public void setMyState(int myState) {
		this.myState = myState;
	}
}
