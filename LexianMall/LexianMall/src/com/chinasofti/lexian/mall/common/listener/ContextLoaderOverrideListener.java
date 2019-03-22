package com.chinasofti.lexian.mall.common.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class ContextLoaderOverrideListener extends ContextLoaderListener{
	
	private final String SYSTEMKEY = "logpath";
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	String value = System.getProperty("logpath",
    			event.getServletContext().getInitParameter("logpath"));
    	System.setProperty(SYSTEMKEY,value);
    	File file = new File(System.getProperty(SYSTEMKEY));
    	
    	if(!file.isDirectory())
    	{
    		file.mkdir();
    	}
    }
}
