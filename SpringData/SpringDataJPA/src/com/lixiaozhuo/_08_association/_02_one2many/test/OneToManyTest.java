package com.lixiaozhuo._08_association._02_one2many.test;

import com.lixiaozhuo._08_association._02_one2many.dao.UsersDao;
import com.lixiaozhuo._08_association._02_one2many.pojo.Roles;
import com.lixiaozhuo._08_association._02_one2many.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 一对多的关联关系测试
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/lixiaozhuo/_08_association/_02_one2many/applicationContext.xml")
public class OneToManyTest {

	@Autowired
	private UsersDao usersDao;
	
	
	/**
	 * 添加用户同时添加角色
	 */
	@Test
	public void test1(){
		//创建角色
		Roles roles = new Roles();
		roles.setRoleName("管理员");
		//创建用户
		Users users =new Users();
		users.setUserAge(30);
		users.setUsername("小王");
		//建立关系
		roles.getUsers().add(users);
		users.setRoles(roles);
		//保存数据
		this.usersDao.save(users);
	}
	
	/**
	 * 根据用户ID查询用户信息，同时查询角色
	 */
	@Test
	public void test2(){
		Users users = this.usersDao.findOne(12);
		System.out.println("用户姓名："+users.getUsername());
		Roles roles = users.getRoles();
		System.out.println(roles);
	}
}
