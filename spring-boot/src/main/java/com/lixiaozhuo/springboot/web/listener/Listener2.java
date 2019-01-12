package com.lixiaozhuo.springboot.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * springBoot整合Listener方式2
 */
public class Listener2 implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Listener2==>init");
	}

}
