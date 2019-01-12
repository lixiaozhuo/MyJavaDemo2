package com.lixiaozhuo.springboot.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/**
 * springBoot整合Listener方式1
 *
 *<listener>
 *	<listener-class>com.lixiaozhuo.Listener1</listener-class>
 *</listener>
 */
@WebListener
public class Listener1 implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Listener1==>init");
	}

}
