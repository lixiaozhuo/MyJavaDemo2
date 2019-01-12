package com.lixiaozhuo.springbootehcache.service;

import java.util.List;

import com.lixiaozhuo.springbootehcache.pojo.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * 用户服务接口
 */
public interface UsersService {
	
	List<Users> findUserAll();
	Users findUserById(Integer id);
	Page<Users> findUserByPage(Pageable pageable);
	void saveUsers(Users users);
}
