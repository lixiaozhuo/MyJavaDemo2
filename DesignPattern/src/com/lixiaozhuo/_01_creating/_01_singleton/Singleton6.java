package com.lixiaozhuo._01_creating._01_singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 懒汉式单例模式(防止反射和反序列化漏洞)
 *
 */
public class Singleton6 implements Serializable {
	//类初始化时，不初始化这个对象（延时加载，真正用的时候再创建）。
	private static Singleton6 instance;
	
	private Singleton6(){ //私有化构造器
		if(instance!=null){
			throw new RuntimeException();
		}
	}
	
	//方法同步，调用效率低！
	public static  synchronized Singleton6 getInstance(){
		if(instance==null){
			instance = new Singleton6();
		}
		return instance;
	}
	
	//反序列化时，如果定义了readResolve()则直接返回此方法指定的对象。而不需要单独再创建新对象！
	private Object readResolve() throws ObjectStreamException {
		return instance;
	}
	
}
