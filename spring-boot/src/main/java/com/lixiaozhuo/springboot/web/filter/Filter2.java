package com.lixiaozhuo.springboot.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 
 *SpringBoot整合Filter方式2
 *
 */
public class Filter2 implements Filter {
	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("进入Filter2");
		arg2.doFilter(arg0, arg1);
		System.out.println("离开Filter2");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
