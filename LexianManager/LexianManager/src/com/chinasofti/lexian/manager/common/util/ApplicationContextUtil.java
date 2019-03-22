package com.chinasofti.lexian.manager.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;

public class ApplicationContextUtil implements ApplicationContextAware,BeanNameAware{
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		synchronized (arg0) {
			if(null==applicationContext)
			  ApplicationContextUtil.applicationContext = arg0;
			else
				throw new ApplicationContextException("cannot set application after spring initial");
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		return (T) applicationContext.getBean(name);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name,Class<?>clazz){
		return (T)applicationContext.getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?>clazz){
		return (T)applicationContext.getBean(clazz);
	}

	@Override
	public void setBeanName(String arg0) {
	}
}
