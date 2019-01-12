package com.lixiaozhuo.springbootexception4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * SpringBoot处理异常方式四
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
