package com.lixiaozhuo.house.service.user;


import com.lixiaozhuo.house.entity.User;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.web.dto.UserDTO;

/**
 * 用户服务接口
 */
public interface IUserService {
    /**
     * 根据手机号注册用户
     * @param telephone 手机号码
     */
    ServiceResult<User> addUserByPhone(String telephone);

    /**
     * 根据用户名获取用户信息
     * @param name 用户名称
     */
    ServiceResult<User> findUserByName(String name);

    /**
     * 根据电话号码获取用户信息
     * @param telephone 手机号码
     */
    ServiceResult<User> findUserByTelephone(String telephone);

    /**
     * 根据id获取用户
     * @param id 用户id
     */
    ServiceResult<UserDTO> findUserById(Long id);

    /**
     * 修改指定属性值
     * @param profile 指定属性
     * @param value 属性值
     */
    ServiceResult modifyUserProfile(String profile, String value);
}
