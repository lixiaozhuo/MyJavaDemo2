package com.lixiaozhuo.house.repository;

import com.lixiaozhuo.house.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * 用户基本信息DAO
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * 根据姓名获取用户
     * @param name 姓名
     * @return 用户信息
     */
    User findByName(String name);

    /**
     * 根据电话号码获取用户
     * @param telephone 电话号码
     * @return 用户信息
     */
    User findUserByPhoneNumber(String telephone);

    /**
     * 更新用户名
     * @param id 用户id
     * @param name 用户名
     */
    @Modifying
    @Query("update User as user set user.name = :name where id = :id")
    void updateUsername(@Param(value = "id") Long id, @Param(value = "name") String name);

    /**
     * 更新电子邮箱
     * @param id 用户id
     * @param email 电子邮箱
     */
    @Modifying
    @Query("update User as user set user.email = :email where id = :id")
    void updateEmail(@Param(value = "id") Long id, @Param(value = "email") String email);

    /**
     * 更新密码
     * @param id 用户id
     * @param password 密码
     */
    @Modifying
    @Query("update User as user set user.password = :password where id = :id")
    void updatePassword(@Param(value = "id") Long id, @Param(value = "password") String password);
}
