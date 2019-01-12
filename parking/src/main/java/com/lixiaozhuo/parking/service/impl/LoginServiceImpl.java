package com.lixiaozhuo.parking.service.impl;

import com.lixiaozhuo.parking.dao.UserDao;
import com.lixiaozhuo.parking.pojo.User;
import com.lixiaozhuo.parking.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录业务
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserDao dao;

    //检测用户登录信息是否合法，合法返回true
    @Override
    public boolean checkLogin(String phone,String password)
    {
        //验证用户手机号码和密码
        List<User> users = dao.findByPhoneAndPassword(phone, password);
        if (users.size()==0){
            return false;
        }
        return true;
    }
}
