package com.lixiaozhuo._04_paging_and_sorting_repository.test;

import java.util.List;

import com.lixiaozhuo._04_paging_and_sorting_repository.dao.UsersDao;
import com.lixiaozhuo._04_paging_and_sorting_repository.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * PagingAndSortingRepository接口测试
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/lixiaozhuo/_04_paging_and_sorting_repository/applicationContext.xml")
public class RepositoryTest {

	@Autowired
	private UsersDao usersDao;
	
	/**
	 * 分页
	 */
	@Test
	public void test1(){
		int page = 2; //page:当前页的索引。注意索引都是从0开始的。
		int size = 3;// size:每页显示3条数据
		Pageable pageable= new PageRequest(page, size);
		Page<Users> p = this.usersDao.findAll(pageable);
		System.out.println("数据的总条数："+p.getTotalElements());
		System.out.println("总页数："+p.getTotalPages());
		List<Users> list = p.getContent();
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 对单列做排序处理
	 */
	@Test
	public void test2(){
		//Sort:该对象封装了排序规则以及指定的排序字段(对象的属性来表示)
		//direction:排序规则
		//properties:指定做排序的属性
		Sort sort = new Sort(Direction.DESC,"userId");
		List<Users> list = (List<Users>)this.usersDao.findAll(sort);
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	/**
	 * 多列的排序处理
	 */
	@Test
	public void test3(){
		//Sort:该对象封装了排序规则以及指定的排序字段(对象的属性来表示)
		//direction:排序规则
		//properties:指定做排序的属性
		Order order1 = new Order(Direction.DESC,"userAge");
		Order order2 = new Order(Direction.ASC,"username");
		Sort sort = new Sort(order1,order2);
		List<Users> list = (List<Users>)this.usersDao.findAll(sort);
		for (Users users : list) {
			System.out.println(users);
		}
	}
}
