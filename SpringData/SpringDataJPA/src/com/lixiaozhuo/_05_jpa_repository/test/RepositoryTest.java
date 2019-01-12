package com.lixiaozhuo._05_jpa_repository.test;

import java.util.List;

import com.lixiaozhuo._05_jpa_repository.dao.UsersDao;
import com.lixiaozhuo._05_jpa_repository.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * JpaRepository接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/lixiaozhuo/_05_jpa_repository/applicationContext.xml")
public class RepositoryTest {

	@Autowired
	private UsersDao usersDao;
	
	/**
	 * 查询全部数据
	 */
	@Test
	public void test1(){
		List<Users> list  = this.usersDao.findAll();
		for (Users users : list) {
			System.out.println(users);
		}
	}
}
