package com.lixiaozhuo._03_behavioral._04_chain_of_responsibility;

/**
 * 主任
 *
 */
public class DirectorLeader extends Leader {

	public DirectorLeader(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveRequest request) {
		if(request.getLeaveDays()<3){
			System.out.println("员工："+request.getEmpName()+"请假，天数："+request.getLeaveDays()+",理由："+request.getReason());
			System.out.println("主任："+this.name+",审批通过！");
		}else{
			if(this.nextLeader!=null){
				this.nextLeader.handleRequest(request);
			}
		}
	}

}
