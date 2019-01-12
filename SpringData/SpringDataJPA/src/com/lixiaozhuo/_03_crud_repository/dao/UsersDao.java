package com.lixiaozhuo._03_crud_repository.dao;



import com.lixiaozhuo._03_crud_repository.pojo.Users;
import org.springframework.data.repository.CrudRepository;


/**
 * CrudRepository接口
 *
 */
public interface UsersDao extends CrudRepository<Users, Integer> {
	
}
