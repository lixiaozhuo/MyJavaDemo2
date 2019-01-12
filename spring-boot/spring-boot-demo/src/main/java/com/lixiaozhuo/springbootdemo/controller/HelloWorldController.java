package com.lixiaozhuo.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
	
	//@Value("${hello.msg2}")
	private String msg;

	@RequestMapping("/hello")
	public String showMsg(){
		return this.msg;
	}
}
