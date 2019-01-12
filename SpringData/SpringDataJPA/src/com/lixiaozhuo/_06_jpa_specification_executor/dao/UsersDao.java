package com.lixiaozhuo._06_jpa_specification_executor.dao;



import com.lixiaozhuo._06_jpa_specification_executor.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * JpaSpecificationExecutor接口
 *
 *注意：JpaSpecificationExecutor<Users>:不能单独使用，需要配合着jpa中的其他接口一起使用
 */
public interface UsersDao extends JpaRepository<Users, Integer>,JpaSpecificationExecutor<Users>{
	
}
