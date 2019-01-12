package com.lixiaozhuo.springboot.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *SpringBoot整合Servlet方式1
 *
 *<servlet>
 *	<servlet-name>Servlet1</servlet-name>
 *	<servlet-class>com.lixiaozhuo.Servlet1</servlet-class>
 *</servlet>
 *
 *<servlet-mapping>
 * <servlet-name>Servlet1</servlet-name>
 * <url-pattern>/first</url-pattern>
 *</servlet-mapping>
 */

@WebServlet(name="Servlet1",urlPatterns="/first")
public class Servlet1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Servlet1");
	}
}
