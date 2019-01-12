package com.lixiaozhuo._03_behavioral._04_chain_of_responsibility;

/**
 * 责任链模式测试
 */
public class Client {
	public static void main(String[] args) {
		Leader leader0 = new DirectorLeader("张三");
		Leader leader1 = new Manager1Leader("李四");
		Leader leader2 = new Manager2Leader("李小四");
		Leader leader3 = new Manager3Leader("王五");
		//组织责任链对象的关系
        leader0.setNextLeader(leader1);
        leader1.setNextLeader(leader2);
        leader2.setNextLeader(leader3);
		
		//开始请假操作
		LeaveRequest req1 = new LeaveRequest("TOM", 15, "回英国老家探亲！");
        leader0.handleRequest(req1);
		
	}
}
