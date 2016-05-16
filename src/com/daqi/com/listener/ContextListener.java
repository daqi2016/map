package com.daqi.com.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextListener implements ServletContextListener {    
	Logger log=LoggerFactory.getLogger(ContextListener.class);
	
	
	
    public void contextInitialized(ServletContextEvent event) { 
    }    
    
    public void contextDestroyed(ServletContextEvent event) {    
    	log.error("监听器销毁..." +   new Date() + ".");    
    }
}
