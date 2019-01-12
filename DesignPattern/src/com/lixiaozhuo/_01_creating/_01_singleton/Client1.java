package com.lixiaozhuo._01_creating._01_singleton;

/**
 * 测试单例模式
 */
public class Client1 {
	
	public static void main(String[] args) {
		Singleton4 s1 = Singleton4.getInstance();
		Singleton4 s2 = Singleton4.getInstance();
		
		System.out.println(s1);
		System.out.println(s2);
		
		System.out.println(Singleton5.INSTANCE== Singleton5.INSTANCE);
	}
}
