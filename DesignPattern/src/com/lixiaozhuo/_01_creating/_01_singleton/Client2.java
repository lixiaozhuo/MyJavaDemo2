package com.lixiaozhuo._01_creating._01_singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 测试反射和反序列化破解单例模式
 *
 */
public class Client2 {
	
	public static void main(String[] args) throws Exception {
		Singleton6 s1 = Singleton6.getInstance();
		Singleton6 s2 = Singleton6.getInstance();
		
		System.out.println(s1);
		System.out.println(s2);

		//////////////////////////////////////////////////////////////////////////////

		//通过反射的方式直接调用私有构造器
//		Class<Singleton6> clazz = (Class<Singleton6>) Class.forName("com.bjsxt._01_singleton.Singleton6");
//		Constructor<Singleton6> c = clazz.getDeclaredConstructor(null);
//		c.setAccessible(true);
//		Singleton6  s3 = c.newInstance();
//		Singleton6  s4 = c.newInstance();
//		System.out.println(s3);
//		System.out.println(s4);

		//////////////////////////////////////////////////////////////////////////////
		//通过反序列化的方式构造多个对象 
		FileOutputStream fos = new FileOutputStream("c:/a.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s1);
		oos.close();
		fos.close();
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c:/a.txt"));
		Singleton6 s3 =  (Singleton6) ois.readObject();
		System.out.println(s3);
		
		
	}
}
