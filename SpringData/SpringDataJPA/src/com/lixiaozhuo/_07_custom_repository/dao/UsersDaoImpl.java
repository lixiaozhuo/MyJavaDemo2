package com.lixiaozhuo._07_custom_repository.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lixiaozhuo._07_custom_repository.pojo.Users;

/**
 * 自定义Repository接口实现
 */
public class UsersDaoImpl implements UsersRepository {

	@PersistenceContext(name="entityManagerFactory")
	private EntityManager em;
	
	@Override
	public Users findUserById(Integer userId) {
		System.out.println("CustomRepository......");
		return this.em.find(Users.class, userId);
	}

}
