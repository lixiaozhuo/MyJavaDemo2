package com.lixiaozhuo.house.service.sms.impl;

import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.service.sms.ISmsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 验证码服务
 */
@Service
public class SmsServiceImpl implements ISmsService, InitializingBean {
    //
    private final static String SMS_CODE_CONTENT_PREFIX = "SMS::CODE::CONTENT";
    //随机产生数组
    private static final String[] NUMS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static final Random random = new Random();

   /* private IAcsClient acsClient;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;*/
    //
    @Value("${aliyun.sms.accessKey}")
    private String accessKey;
    //
    @Value("${aliyun.sms.accessKeySecret}")
    private String secertKey;
    //
    @Value("${aliyun.sms.template.code}")
    private String templateCode;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 设置超时时间
        //System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        //System.setProperty("sun.net.client.defaultReadTimeout", "10000");
       /* IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, secertKey);
        String product = "Dysmsapi";
        String domain = "dysmsapi.aliyuncs.com";
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        this.acsClient = new DefaultAcsClient(profile);*/
    }

    /**
     * 发送验证码到指定手机并缓存验证码10分钟及请求间隔时间1分钟
     */
    @Override
    public ServiceResult<String> sendSmsCode(String telephone) {
        /*String gapKey = "SMS::CODE::INTERVAL::" + telephone;
        String result = redisTemplate.opsForValue().get(gapKey);
        if (result != null) {
            return new ServiceResult<String>(false, "请求次数太频繁");
        }
        String code = generateRandomSmsCode();
        String templateParam = String.format("{\"code\": \"%s\"}", code);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(telephone);
        request.setTemplateParam(templateParam);
        request.setTemplateCode(templateCode);
        request.setSignName("寻屋");
        boolean ofSuccess = false;
        try {
            SendSmsResponse response = acsClient.getAcsResponse(request);
            if ("OK".equals(response.getCode())) {
                ofSuccess = true;
            } else {
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (ofSuccess) {
            redisTemplate.opsForValue().set(gapKey, code, 60, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(SMS_CODE_CONTENT_PREFIX + telephone, code, 10, TimeUnit.MINUTES);
            return ServiceResult.ofSuccess(code);
        } else {
            return new ServiceResult<String>(false, "服务忙，请稍后重试");
        }*/
        return null;
    }

    /**
     * 获取缓存中的验证码
     */
    @Override
    public ServiceResult<String> getSmsCode(String telephone) {
        //this.redisTemplate.opsForValue().get(SMS_CODE_CONTENT_PREFIX + telephone);
        return null;
    }

    /**
     * 移除指定手机号的验证码缓存
     */
    @Override
    public void removeSmsCode(String telephone) {
        //this.redisTemplate.delete(SMS_CODE_CONTENT_PREFIX + telephone);
    }

    /**
     * 6位验证码生成器
     */
    private static String generateRandomSmsCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(10);
            sb.append(NUMS[index]);
        }
        return sb.toString();
    }
}
