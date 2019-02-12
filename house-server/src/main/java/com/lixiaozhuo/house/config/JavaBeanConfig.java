package com.lixiaozhuo.house.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JavaBean配置
 */
@Configuration
public class JavaBeanConfig {
    /**
     * java对象自动映射工具
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
