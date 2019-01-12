package com.lixiaozhuo.springbootscheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot:Scheduled定时任务
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootScheduledApplication.class, args);
    }

}

