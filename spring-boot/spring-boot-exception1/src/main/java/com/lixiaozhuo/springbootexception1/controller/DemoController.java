package com.lixiaozhuo.springbootexception1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringBoot处理异常方式一：自定义错误页面内容
 *
 *
 */
@Controller
public class DemoController {
	
	@RequestMapping("/show1")
	public String showInfo(){
		String str = null;
		str.length();
		return "index";
	}
	
	@RequestMapping("/show2")
	public String showInfo2(){
		int a = 10/0;
		return "index";
	}
	
	
}
