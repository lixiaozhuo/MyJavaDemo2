package com.lixiaozhuo.springbootquartz.quartz;

import java.util.Date;

import com.lixiaozhuo.springbootquartz.service.UsersService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * Job类
 */
public class QuartzDemo implements Job {
	
	@Autowired
	private UsersService usersService;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Execute...."+new Date());
		this.usersService.addUsers();
	}
}
