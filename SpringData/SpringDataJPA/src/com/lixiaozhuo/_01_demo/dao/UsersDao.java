package com.lixiaozhuo._01_demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lixiaozhuo._01_demo.pojo.Users;

public interface UsersDao extends JpaRepository<Users, Integer> {

}
