package com.lixiaozhuo._03_behavioral._04_chain_of_responsibility;

/**
 * 副总经理
 *
 */
public class Manager2Leader extends Leader {

	public Manager2Leader(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveRequest request) {
		if(request.getLeaveDays()<20){
			System.out.println("员工："+request.getEmpName()+"请假，天数："+request.getLeaveDays()+",理由："+request.getReason());
			System.out.println("副总经理："+this.name+",审批通过！");
		}else{
			if(this.nextLeader!=null){
				this.nextLeader.handleRequest(request);
			}
		}
	}

}
