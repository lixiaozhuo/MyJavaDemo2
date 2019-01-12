package com.lixiaozhuo.parking.service;

import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.User;
import org.springframework.data.domain.Page;

/**
 * 用户业务接口
 */
public interface IUserService {
    /**
     * 删除用户
     * @param id
     */
    void deleteUser(long id);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 获取用户
     * @param id
     * @return
     */
    User getUser(long id);

    /**
     * 检查手机号码是否注册
     * @param phone
     * @return
     */
    boolean checkPhone(String phone);

    /**
     * 根据手机号获取用户
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 获取分页后的用户列表
     * @param page
     * @return
     */
    Page<User> listUser(int page);

    /**
     * 根据条件获取分页后的用户列表
     * @param condition
     * @param page
     * @return
     */
    Page<User> listUser(Condition condition, int page);

    /**
     * 根据手机号码更新用户密码
     * @param phone
     * @param newPassword
     */
    void updatePassword(String phone, String newPassword);
}
