package com.lixiaozhuo.springboot.web;


import com.lixiaozhuo.springboot.web.filter.Filter2;
import com.lixiaozhuo.springboot.web.listener.Listener2;
import com.lixiaozhuo.springboot.web.servlet.Servlet2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * SpringBoot整合Servlet Filter Listener方式2
 */
@SpringBootApplication
public class WebApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication2.class, args);
	}
	
	/**
	 * 注册Servlet
	 */
	@Bean
	public ServletRegistrationBean getServletRegistrationBean(){
		ServletRegistrationBean<Servlet2> bean = new ServletRegistrationBean<>(new Servlet2());
		bean.addUrlMappings("/second");
		return bean;
	}
	
	/**
	 * 注册Filter
	 */
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean(){
		FilterRegistrationBean<Filter2> bean = new FilterRegistrationBean<>(new Filter2());
		//bean.addUrlPatterns(new String[]{"*.do","*.jsp"});
		bean.addUrlPatterns("/second");
		return bean;
	}

	/**
	 * 注册listener
	 */
	@Bean
	public ServletListenerRegistrationBean<Listener2> getServletListenerRegistrationBean(){
		ServletListenerRegistrationBean<Listener2> bean= new ServletListenerRegistrationBean<>(new Listener2());
		return bean;
	}
}
