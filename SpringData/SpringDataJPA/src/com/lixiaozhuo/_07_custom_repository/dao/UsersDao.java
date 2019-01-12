package com.lixiaozhuo._07_custom_repository.dao;



import com.lixiaozhuo._07_custom_repository.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * 用户自定义Repository接口
 */
public interface UsersDao extends JpaRepository<Users, Integer>,JpaSpecificationExecutor<Users>,UsersRepository{
	
}
