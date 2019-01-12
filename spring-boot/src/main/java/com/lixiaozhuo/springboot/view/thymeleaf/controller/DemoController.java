package com.lixiaozhuo.springboot.view.thymeleaf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lixiaozhuo.springboot.view.thymeleaf.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * Thymeleaf入门案例
 *
 *
 */
@Controller
public class DemoController {
	@RequestMapping("/show1")
	public String showInfo(HttpServletRequest request,Model model){
		model.addAttribute("msg", "Thymeleaf 第一个案例");
		model.addAttribute("key", new Date());
		return "index1";
	}
	
	@RequestMapping("/show2")
	public String showInfo2(Model model){
		model.addAttribute("sex", "女");
		model.addAttribute("id","1");
		return "index2";
	}
	
	@RequestMapping("/show3")
	public String showInfo3(Model model){
		List<Users> list = new ArrayList<>();
		list.add(new Users(1,"张三",20));
		list.add(new Users(2,"李四",22));
		list.add(new Users(3,"王五",24));
		model.addAttribute("list", list);
		return "index3";
	}
	
	@RequestMapping("/show4")
	public String showInfo4(Model model){
		Map<String, Users> map = new HashMap<>();
		map.put("u1", new Users(1,"张三",20));
		map.put("u2", new Users(2,"李四",22));
		map.put("u3", new Users(3,"王五",24));
		model.addAttribute("map", map);
		return "index4";
	}
	
	@RequestMapping("/show5")
	public String showInfo5(HttpServletRequest request,Model model){
		request.setAttribute("req", "HttpServletRequest");
		request.getSession().setAttribute("session", "HttpSession");
		request.getSession().getServletContext().setAttribute("app", "Application");
		return "index5";
	}

	@RequestMapping("/{page}")
	public String showInfo(@PathVariable String page, Integer id, String name){
		System.out.println(id+"--"+name);
		return page;
	}
	
}
