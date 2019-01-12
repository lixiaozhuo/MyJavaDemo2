package com.lixiaozhuo.springbootmybatis;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot整合Mybatis 启动类
 */
@SpringBootApplication
@MapperScan("com.lixiaozhuo.springbootmybatis.mapper") //@MapperScan 用户扫描MyBatis的Mapper接口
public class SpringBootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

}

