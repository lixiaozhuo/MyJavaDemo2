package com.lixiaozhuo._07_custom_repository.test;

import com.lixiaozhuo._07_custom_repository.dao.UsersDao;
import com.lixiaozhuo._07_custom_repository.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * JpaRepository接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/lixiaozhuo/_07_custom_repository/applicationContext.xml")
public class RepositoryTest {

	@Autowired
	private UsersDao usersDao;
	
	/**
	 * 需求：根据用户ID查询数据
	 */
	@Test
	public void test1(){
		Users users = this.usersDao.findUserById(5);
		System.out.println(users);
	}
}
