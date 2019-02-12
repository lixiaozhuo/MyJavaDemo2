package com.lixiaozhuo.house.service.sms;

import com.lixiaozhuo.house.service.ServiceResult;

/**
 * 验证码服务接口
 */
public interface ISmsService {
    /**
     * 发送验证码到指定手机并缓存验证码10分钟及请求间隔时间1分钟
     * @param telephone 电话号码
     * @return 服务结果
     */
    ServiceResult<String> sendSmsCode(String telephone);

    /**
     * 获取缓存中的验证码
     * @param telephone 手机号码
     * @return 验证码
     */
    ServiceResult<String>  getSmsCode(String telephone);

    /**
     * 移除指定手机号的验证码缓存
     * @param telephone 手机号码
     */
    void removeSmsCode(String telephone);
}
