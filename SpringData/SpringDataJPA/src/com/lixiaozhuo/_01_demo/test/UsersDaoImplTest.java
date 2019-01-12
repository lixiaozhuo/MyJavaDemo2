package com.lixiaozhuo._01_demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lixiaozhuo._01_demo.dao.UsersDao;
import com.lixiaozhuo._01_demo.pojo.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * SpringData测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/lixiaozhuo/_01_demo/applicationContext.xml")
public class UsersDaoImplTest {

	@Autowired
	private UsersDao usersDao;
	
	
	/**
	 * 添加用户
	 */
	@Test
	@Transactional// 在测试类对于事务提交方式默认的是回滚。
	@Rollback(false)//取消自动回滚
	public void testInsertUsers(){
		Users users = new Users();
		users.setUserAge(24);
		users.setUsername("张三");
		this.usersDao.save(users);
	}

	@PersistenceContext(name="entityManagerFactory")
	private EntityManager em;
	@Test
	public void test1(){
		//org.springframework.data.jpa.repository.support.SimpleJpaRepository@62e6a3ec
		System.out.println(this.usersDao);
		//class com.sun.proxy.$Proxy30  代理对象是基于JDK的动态代理方法创建的
		System.out.println(this.usersDao.getClass());

		//底层原理
		JpaRepositoryFactory factory = new JpaRepositoryFactory(em);

		//getRepository(UsersDao.class)可以帮助我们为接口生成实现类, 而这个实现类为SimpleJpaRepository
		//要求:该接口必须是继承Repository接口
		UsersDao ud = factory.getRepository(UsersDao.class);
		System.out.println(ud);
		System.out.println(ud.getClass());
	}
	
}
