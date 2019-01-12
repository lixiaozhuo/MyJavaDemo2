package com.lixiaozhuo.springboottest.dao;

import org.springframework.stereotype.Repository;

/**
 * DAO
 */
@Repository
public class UserDaoImpl {

	public void saveUser(){
		System.out.println("insert into users.....");
	}
}
