package com.lixiaozhuo._02_structural._06_proxy.dynamic_proxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 */
public class Client {
	public static void main(String[] args) {
		//真实对象
		Star realStar = new RealStar();
		StarHandler handler = new StarHandler(realStar);

		//生成代理对象
		Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[]{Star.class}, handler);
		
		proxy.sing();
		
	}
	
}