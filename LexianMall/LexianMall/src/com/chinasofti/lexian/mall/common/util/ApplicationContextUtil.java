package com.chinasofti.lexian.mall.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextUtil implements ApplicationContextAware,BeanNameAware{
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		 if(null==applicationContext)
		 {
			 synchronized (arg0) {
				  ApplicationContextUtil.applicationContext = arg0;
		       }
		 }
	}
	 
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		return (T) applicationContext.getBean(name);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name,Class<? extends T>clazz){
		return (T)applicationContext.getBean(name);
	}
	

	public static <T> T getBean(Class<? extends T>clazz){
		return (T)applicationContext.getBean(clazz);
	}
	
	public static <T> T getUnitBean(Class<? extends T>clazz){
		applicationContext  =  new FileSystemXmlApplicationContext("resource/applicationContext.xml");
		return (T)applicationContext.getBean(clazz);
	}

	@Override
	public void setBeanName(String arg0) {
	}
}
