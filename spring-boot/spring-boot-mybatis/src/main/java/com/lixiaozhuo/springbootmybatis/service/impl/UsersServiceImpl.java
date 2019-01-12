package com.lixiaozhuo.springbootmybatis.service.impl;

import java.util.List;


import com.lixiaozhuo.springbootmybatis.mapper.UsersMapper;
import com.lixiaozhuo.springbootmybatis.pojo.Users;
import com.lixiaozhuo.springbootmybatis.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 用户信息业务
 */
@Service
@Transactional
public class UsersServiceImpl implements IUsersService {

	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public void addUser(Users users) {
		this.usersMapper.insertUser(users);
	}

	@Override
	public List<Users> findUserAll() {
		return this.usersMapper.selectUsersAll();
	}

	@Override
	public Users findUserById(Integer id) {
		return this.usersMapper.selectUsersById(id);
	}

	@Override
	public void updateUser(Users users) {
		this.usersMapper.updateUser(users);
	}

	@Override
	public void deleteUserById(Integer id) {
		this.usersMapper.deleteUserById(id);
	}
}
