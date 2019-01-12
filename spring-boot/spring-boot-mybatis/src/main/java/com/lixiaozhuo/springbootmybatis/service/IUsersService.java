package com.lixiaozhuo.springbootmybatis.service;

import com.lixiaozhuo.springbootmybatis.pojo.Users;

import java.util.List;

/**
 * 用户信息业务接口
 */
public interface IUsersService {
	
	void addUser(Users users);
	List<Users> findUserAll();
	Users findUserById(Integer id);
	void updateUser(Users users);
	void deleteUserById(Integer id);
} 
