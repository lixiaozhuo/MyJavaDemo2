package com.lixiaozhuo._03_behavioral._04_chain_of_responsibility;

/**
 * 抽象类
 *
 */
public abstract class Leader {
	protected String name;
    //责任链上的后继对象
	protected Leader nextLeader;
	
	public Leader(String name) {
		super();
		this.name = name;
	}
	
	//设定责任链上的后继对象
	public void setNextLeader(Leader nextLeader) {
		this.nextLeader = nextLeader;
	}
	
	
	/**
	 * 处理请求的核心的业务方法
	 */
	public abstract void handleRequest(LeaveRequest request);
	
	
	
}
