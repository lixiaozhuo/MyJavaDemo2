package com.lixiaozhuo.house.repository;

import java.util.List;

import com.lixiaozhuo.house.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * 角色数据DAO
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    /**
     * 通过用户id获取角色集合
     * @param userId  用户id
     * @return 角色集合
     */
    List<Role> findRolesByUserId(Long userId);
}
