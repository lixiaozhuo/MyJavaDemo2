package com.lixiaozhuo._07_custom_repository.dao;

import com.lixiaozhuo._07_custom_repository.pojo.Users;

/**
 * 自定义Repository接口
 */
public interface UsersRepository {

	Users findUserById(Integer userId);
}
