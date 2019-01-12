package com.lixiaozhuo.parking.service;

/**
 * 登录业务接口
 */
public interface ILoginService {
    /**
     * 检测用户登录信息是否合法，合法返回true
     * @param phone
     * @param password
     * @return
     */
    boolean checkLogin(String phone,String password);
}
