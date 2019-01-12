package com.lixiaozhuo.springbootehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * SpringBoot:Ehcache
 */
@SpringBootApplication
@EnableCaching
public class SpringBootEhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEhcacheApplication.class, args);
    }

}

