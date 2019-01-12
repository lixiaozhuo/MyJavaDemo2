package com.lixiaozhuo._02_repository.test;

import java.util.List;

import com.lixiaozhuo._02_repository.dao.UsersDao;
import com.lixiaozhuo._02_repository.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/lixiaozhuo/_02_repository/applicationContext.xml")
public class RepositoryTest {

	@Autowired
	private UsersDao usersDao;
	
	/**
	 * 需求：使用用户名作为查询条件
	 */
	@Test
	public void test1(){
		/**
		 * 判断相等的条件，有三种表示方式
		 * 1,什么都不写，默认的就是做相等判断
		 * 2,Is
		 * 3,Equal
		 */
		List<Users> list = this.usersDao.findByUsernameIs("王五");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 需求：根据用户姓名做Like处理
	 * Like:条件关键字
	 */
	@Test
	public void test2(){
		List<Users> list = this.usersDao.findByUsernameLike("王%");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 需求：查询名称为王五，并且他的年龄大于等于22岁
	 */
	@Test
	public void test3(){
		List<Users> list = this.usersDao.findByUsernameAndUserAgeGreaterThanEqual("王五", 22);
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 测试@Query查询 JPQL
	 */
	@Test
	public void test4(){
		List<Users> list = this.usersDao.queryUserByNameUseJPQL("王五");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 测试@Query查询 JPQL
	 */
	@Test
	public void test5(){
		List<Users> list = this.usersDao.queryUserByLikeNameUseJPQL("王%");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 测试@Query查询 JPQL
	 */
	@Test
	public void test6(){
		List<Users> list = this.usersDao.queryUserByNameAndAge("王五", 22);
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 测试@Query查询 SQL
	 */
	@Test
	public void test7(){
		List<Users> list = this.usersDao.queryUserByNameUseSQL("王五");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 测试@Query查询 SQL
	 */
	@Test
	public void test8(){
		List<Users> list = this.usersDao.queryUserByLikeNameUseSQL("王%");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 测试@Query查询 SQL
	 */
	@Test
	public void test9(){
		List<Users> list = this.usersDao.queryUserByNameAndAgeUseSQL("王五", 22);
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 测试@Query update
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void test10(){
		this.usersDao.updateUserAgeById(24, 5);
	}
}
