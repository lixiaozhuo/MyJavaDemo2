package com.lixiaozhuo.parking.dao;

import com.lixiaozhuo.parking.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 用户Repository接口
 */
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    //验证用户手机号和密码是否匹配
    List<User> findByPhoneAndPassword(String phone,String password);

    //根据手机号获取用户
    User findByPhone(String phone);
}
