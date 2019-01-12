package com.lixiaozhuo._01_demo.dao;

import java.util.List;

import com.lixiaozhuo._01_demo.pojo.Users;

/**
 * 用户DAO接口
 */
public interface IUsersDao {
	
	void insertUsers(Users users);

	void updateUsers(Users users);

	void deleteUsers(Users users);

	Users selectUsersById(Integer userId);
	
	List<Users> selectUserByName(String username);
	
	List<Users> selectUserByNameUseSQL(String username);
	
	List<Users> selectUserByNameUseCriteria(String username);
}
