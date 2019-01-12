package com.lixiaozhuo._03_behavioral._02_oberserver._02_jdk;

/**
 * 观察者模式测试(JDK)
 */
public class Client {
	public static void main(String[] args) {
		//创建目标主题
		ConcreteSubject subject = new ConcreteSubject();
		
		//创建观察者
		ObserverA obs1 = new ObserverA();
		ObserverA obs2 = new ObserverA();
		ObserverA obs3 = new ObserverA();
		
		//将上面三个观察者对象添加到目标主题的观察者容器中
		subject.addObserver(obs1);
		subject.addObserver(obs2);
		subject.addObserver(obs3);
		
		//改变主题对象的状态
		subject.setState(3000);
		System.out.println("===============状态修改了！");
		//观察者的状态发生了变化
		System.out.println(obs1.getMyState());
		System.out.println(obs2.getMyState());
		System.out.println(obs3.getMyState());

        //改变主题对象的状态
        subject.setState(600);
		System.out.println("===============状态修改了！");
		//观察者的状态发生了变化
		System.out.println(obs1.getMyState());
		System.out.println(obs2.getMyState());
		System.out.println(obs3.getMyState());		
		
	}
}
