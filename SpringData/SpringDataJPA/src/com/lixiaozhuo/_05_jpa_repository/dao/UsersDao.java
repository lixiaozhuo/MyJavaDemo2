package com.lixiaozhuo._05_jpa_repository.dao;



import com.lixiaozhuo._05_jpa_repository.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * JpaRepository接口
 *
 */
public interface UsersDao extends JpaRepository<Users, Integer>{
	
}
