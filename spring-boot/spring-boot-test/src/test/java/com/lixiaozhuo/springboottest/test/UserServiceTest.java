package com.lixiaozhuo.springboottest.test;

import com.lixiaozhuo.springboottest.SpringBootTestApplication;
import com.lixiaozhuo.springboottest.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * SpringBoot测试类
 *@RunWith:启动器 
 *SpringJUnit4ClassRunner.class：让junit与spring环境进行整合
 *
 *@SpringBootTest(classes={App.class})
 * 1,当前类为springBoot的测试类
 * 2,加载SpringBoot启动类。启动springBoot
 *
 *junit与spring整合 @ContextConfiguration("classpath:applicationContext.xml")
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes={SpringBootTestApplication.class})
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void testAddUser(){
		this.userServiceImpl.addUser();
	}
}
