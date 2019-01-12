package com.lixiaozhuo._03_behavioral._09_mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * 经理(中介者)
 */
public class President implements Mediator {
	
	private Map<String,Department> map = new HashMap<String , Department>();
	
	@Override
	public void command(String dName) {
		map.get(dName).selfAction();
	}

	@Override
	public void register(String dName, Department d) {
		map.put(dName, d);
	}

}
