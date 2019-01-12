package com.lixiaozhuo.springbootjpa.test;

import com.lixiaozhuo.springbootjpa.SpringBootJpaApplication;
import com.lixiaozhuo.springbootjpa.dao.UsersRepository;
import com.lixiaozhuo.springbootjpa.pojo.Roles;
import com.lixiaozhuo.springbootjpa.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 一对多关联关系测试
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= SpringBootJpaApplication.class)
public class OneToManyTest {
	
	@Autowired
	private UsersRepository usersRepository;
	
	/**
	 * 一对多关联关系的添加
	 */
	@Test
	public void testSave(){
		//创建一个用户
		Users users = new Users();
		users.setAddress("天津");
		users.setAge(32);
		users.setName("小刚");
		
		//创建一个角色
		Roles roles = new Roles();
		roles.setRoleName("管理员");
		
		//关联
		roles.getUsers().add(users);
		users.setRoles(roles);
		
		//保存
		this.usersRepository.save(users);
	}
	
	/**
	 * 一对多关联关系的查询
	 */
	@Test
	public void testFind(){
		Users findOne = this.usersRepository.findById(1).get();
		System.out.println(findOne);
		Roles roles = findOne.getRoles();
		System.out.println(roles.getRoleName());
	}
}
