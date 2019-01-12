package com.lixiaozhuo._03_behavioral._04_chain_of_responsibility;

/**
 * 总经理
 */
public class Manager3Leader extends Leader {

	public Manager3Leader(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveRequest request) {
		if(request.getLeaveDays()<30){
			System.out.println("员工："+request.getEmpName()+"请假，天数："+request.getLeaveDays()+",理由："+request.getReason());
			System.out.println("总经理："+this.name+",审批通过！");
		}else{
			System.out.println("莫非"+request.getEmpName()+"想辞职，居然请假"+request.getLeaveDays()+"天！");
		}
	}

}
