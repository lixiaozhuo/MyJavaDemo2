package com.lixiaozhuo._01_creating._01_singleton;

/**
 * 懒汉式单例模式
 *
 */
public class Singleton2 {
	
	//类初始化时，不初始化这个对象（延时加载，真正用的时候再创建）。
	private static Singleton2 instance;
	
	private Singleton2(){ //私有化构造器
	}
	
	//方法同步，调用效率低！
	public static  synchronized Singleton2 getInstance(){
		if(instance==null){
			instance = new Singleton2();
		}
		return instance;
	}
	
}
