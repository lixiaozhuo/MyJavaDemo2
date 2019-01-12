package com.lixiaozhuo.springbootjpa.test;

import java.util.Set;

import com.lixiaozhuo.springbootjpa.SpringBootJpaApplication;
import com.lixiaozhuo.springbootjpa.dao.RolesRepository;
import com.lixiaozhuo.springbootjpa.pojo.Menus;
import com.lixiaozhuo.springbootjpa.pojo.Roles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 * 多对多的关联关系的测试
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= SpringBootJpaApplication.class)
public class ManyToManyTest {

	@Autowired
	private RolesRepository rolesRepository;
	
	/**
	 * 添加测试
	 */
	@Test
	public void testSave(){
		//创建角色对象
		Roles r = new Roles();
		r.setRoleName("项目经理");
		
		//创建菜单对象
		Menus menus = new Menus();
		menus.setMenusName("xxxx管理系统");
		menus.setFatherId(0);
		
		Menus menus2 = new Menus();
		menus2.setFatherId(1);
		menus2.setMenusName("项目管理");
		//关联
		r.getMenus().add(menus);
		r.getMenus().add(menus2);
		menus.getRoles().add(r);
		menus2.getRoles().add(r);
		//保存
		this.rolesRepository.save(r);
	}
	
	/**
	 * 查询操作
	 */
	@Test
	public void testFind(){
		Roles roles = this.rolesRepository.findById(1).get();
		System.out.println(roles.getRoleName());
		Set<Menus> menus = roles.getMenus();
		for (Menus menus2 : menus) {
			System.out.println(menus2);
		}
	}
}
