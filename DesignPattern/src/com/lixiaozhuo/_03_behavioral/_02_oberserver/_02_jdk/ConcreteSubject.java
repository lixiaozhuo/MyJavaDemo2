package com.lixiaozhuo._03_behavioral._02_oberserver._02_jdk;

import java.util.Observable;

//具体主题(被观察者)
public class ConcreteSubject extends Observable {
	
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
        //表示目标对象已经做了更改
        setChanged();
        //通知所有的观察者
        notifyObservers(state);
	}
	
	
	
	
}
