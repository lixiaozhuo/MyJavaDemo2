package com.lixiaozhuo._08_association._01_one2one.dao;



import com.lixiaozhuo._08_association._01_one2one.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * 一对一关联关系操作
 */
public interface UsersDao extends JpaRepository<Users, Integer>,JpaSpecificationExecutor<Users>{
}
