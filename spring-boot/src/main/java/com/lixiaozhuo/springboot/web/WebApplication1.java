package com.lixiaozhuo.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *SpringBoot整合Servlet Filter Listener方式1
 *
 */
@SpringBootApplication
@ServletComponentScan
public class WebApplication1 {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication1.class, args);
	}

}
