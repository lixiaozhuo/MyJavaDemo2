package com.lixiaozhuo.springbootjpa.dao;

import com.lixiaozhuo.springbootjpa.pojo.Users;
import org.springframework.data.repository.CrudRepository;


/**
 * CrudRepository接口
 *
 *
 */
public interface UsersRepositoryCrudRepository extends CrudRepository<Users, Integer> {

}
