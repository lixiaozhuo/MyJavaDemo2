package com.lixiaozhuo.house.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * SpringSecurity配置
 */
//@Configuration
//关闭验证
@EnableAutoConfiguration(exclude ={org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SpringSecurityConfig {
}
