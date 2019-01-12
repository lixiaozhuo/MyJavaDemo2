package com.lixiaozhuo._03_behavioral._03_iterator;

/**
 * 迭代子模式测试
 */
public class Client {
	
	public static void main(String[] args) {
		ConcreteMyAggregate cma = new ConcreteMyAggregate();
		cma.addObject("aa");
		cma.addObject("bb");
		cma.addObject("cc");
		
		MyIterator iterator = cma.createIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.getCurrentObj());
			iterator.next();
		}
		
	}
}
