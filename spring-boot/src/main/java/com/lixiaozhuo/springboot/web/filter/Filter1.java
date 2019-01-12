package com.lixiaozhuo.springboot.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *SpringBoot整合Filter方式
 *<filter>
 *	<filter-name>Filter1</filter-name>
 *	<filter-class>com.lixiaozhuo.Filter1</filter-class>
 *</filter>
 *<filter-mapping>
 *	<filter-name>Filter1</filter-name>
 *	<url-pattern>/first</url-pattern>
 *</filter-mapping>
 */
//@WebFilter(filterName="Filter1",urlPatterns={"*.do","*.jsp"})
@WebFilter(filterName="Filter1",urlPatterns="/first")
public class Filter1 implements Filter {

	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("进入Filter1");
		arg2.doFilter(arg0, arg1);
		System.out.println("离开Filter1");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
