package com.lixiaozhuo._08_association._03_many2many.test;

import java.util.Set;

import com.lixiaozhuo._08_association._03_many2many.dao.RolesDao;
import com.lixiaozhuo._08_association._03_many2many.pojo.Menus;
import com.lixiaozhuo._08_association._03_many2many.pojo.Roles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 多对多关联关系
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/lixiaozhuo/_08_association/_03_many2many/applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private RolesDao rolesDao;

    /**
     * 添加角色同时添加菜单
     */
    @Test
    public void test1() {
        //创建角色对象
        Roles roles = new Roles();
        roles.setRoleName("超级管理员");

        //创建菜单对象    XXX管理平台 --->用户管理
        Menus menus = new Menus();
        menus.setMenusName("XXX管理平台");
        menus.setFatherId(-1);
        menus.setMenusUrl(null);

        //用户管理菜单
        Menus menus1 = new Menus();
        menus1.setMenusName("用户管理");
        menus1.setFatherId(1);
        menus1.setMenusUrl(null);

        //建立关系
        roles.getMenus().add(menus);
        roles.getMenus().add(menus1);

        menus.getRoles().add(roles);
        menus1.getRoles().add(roles);

        //保存数据
        this.rolesDao.save(roles);
    }

    /**
     * 查询角色
     */
    @Test
    public void test2() {
        Roles roles = this.rolesDao.findOne(3);
        System.out.println("角色信息：" + roles);
        Set<Menus> menus = roles.getMenus();
        for (Menus menus2 : menus) {
            System.out.println("菜单信息：" + menus2);
        }
    }

}
