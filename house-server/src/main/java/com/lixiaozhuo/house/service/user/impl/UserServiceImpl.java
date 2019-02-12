package com.lixiaozhuo.house.service.user.impl;

import com.lixiaozhuo.house.base.uitls.LoginUserUtil;
import com.lixiaozhuo.house.entity.Role;
import com.lixiaozhuo.house.entity.User;
import com.lixiaozhuo.house.repository.RoleRepository;
import com.lixiaozhuo.house.repository.UserRepository;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.service.user.IUserService;
import com.lixiaozhuo.house.web.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * 用户业务
 */
@Service
public class UserServiceImpl implements IUserService {
    //java对象自动映射工具
    @Autowired
    private ModelMapper modelMapper;
    //用户信息DAO
    @Autowired
    private UserRepository userRepository;
    //角色信息DOA
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 根据手机号注册用户
     */
    @Override
    @Transactional
    public ServiceResult<User> addUserByPhone(String telephone) {
        //设置用户属性
        User user = new User();
        //设置手机号码
        user.setPhoneNumber(telephone);
        //默认名称为手机号码前4位+"****"+后几位
        user.setName(telephone.substring(0, 3) + "****" + telephone.substring(7, telephone.length()));
        //设置创建时间
        user.setCreateTime(new Date());
        //设置最后登录时间
        user.setLastLoginTime(new Date());
        //设置最近更新时间
        user.setLastUpdateTime(new Date());
        //保存用户
        user = userRepository.save(user);
        //设置角色信息
        Role role = new Role();
        //设置用户角色
        role.setName("USER");
        //关联用户id
        role.setUserId(user.getId());
        //保存角色
        roleRepository.save(role);
        return ServiceResult.ofSuccess(user);
    }

    /**
     * 根据用户名获取用户
     */
    @Override
    public ServiceResult<User> findUserByName(String userName) {
        User user = userRepository.findByName(userName);
        //用户为空
        if (user == null) {
            return ServiceResult.notFound();
        }
        //获取角色
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            //抛出没有权限异常
            //throws ;
        }
        return ServiceResult.ofSuccess(user);
    }

    /**
     * 根据电话号码获取用户
     */
    @Override
    public ServiceResult<User> findUserByTelephone(String telephone) {
        User user = userRepository.findUserByPhoneNumber(telephone);
        //用户为空
        if (user == null) {
            return ServiceResult.notFound();
        }
        //获取角色集合
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            //抛出没有权限异常
            //throws ;
        }
        return ServiceResult.ofSuccess(user);
    }

    /**
     * 根据id获取用户
     * @param id id
     * @return 服务结果集
     */
    @Override
    public ServiceResult<UserDTO> findUserById(Long id) {
        //参数检查
        if (id == null) {
            return ServiceResult.ofMessage(false,"参数为空");
        }
        Optional<User> optional = userRepository.findById(id);
        //用户为空
        if (!optional.isPresent()) {
            return ServiceResult.notFound();
        }
        //将User转换成UserDTO
        return ServiceResult.ofSuccess(modelMapper.map( optional.get(), UserDTO.class));
    }

    /**
     * 修改指定属性值
     * @param profile 指定属性
     * @param value 属性值
     * @return 服务结果
     */
    @Override
    @Transactional
    public ServiceResult modifyUserProfile(String profile, String value) {
        //参数检查
        if (profile == null || profile.isEmpty()) {
            return ServiceResult.ofMessage(false, "指定属性不可以为空");
        }
        //获取当前登录用户id
        Long userId = LoginUserUtil.getLoginUserId();
        //修改不同属性
        switch (profile) {
            case "name"://修改用户名属性
                userRepository.updateUsername(userId, value);
                break;
            case "email"://修改邮箱属性
                userRepository.updateEmail(userId, value);
                break;
            case "password"://修改密码属性
                userRepository.updatePassword(userId, value);
                break;
            default:
                return ServiceResult.ofMessage(false, "不支持的属性");
        }
        return ServiceResult.ofSuccess();
    }








}
