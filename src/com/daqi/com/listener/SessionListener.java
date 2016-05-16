package com.daqi.com.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	

	
	public SessionListener() {
		
	}

	/** 以下是实现HttpSessionListener中的方法 **/
	public void sessionCreated(HttpSessionEvent se) {
	}

	public void sessionDestroyed(HttpSessionEvent se) {
	}

	/** 以下是实现HttpSessionAttributeListener中的方法 **/
	public void attributeAdded(HttpSessionBindingEvent se) {
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

}