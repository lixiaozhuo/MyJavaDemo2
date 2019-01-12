package com.lixiaozhuo._08_association._03_many2many.dao;

import com.lixiaozhuo._08_association._03_many2many.pojo.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 多对多关联关系
 *
 */
public interface RolesDao extends JpaRepository<Roles, Integer> {

}
