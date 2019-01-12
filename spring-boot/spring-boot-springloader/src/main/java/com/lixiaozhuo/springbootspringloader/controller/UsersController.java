package com.lixiaozhuo.springbootspringloader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SpringBoot----SpringLoader
 *
 *
 */
@Controller
public class UsersController {

	@RequestMapping("/show")
	public String showPage(){
		System.out.println("ShowPageh");
		return "index";
	}
}
