package com.lixiaozhuo.springbootdevtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringBoot热部署
 *
 *
 */
@Controller
public class UsersController {

	@RequestMapping("/show")
	public String showPage(){
		System.out.println("ShowPage......initggg");
		return "index";
	}
}
