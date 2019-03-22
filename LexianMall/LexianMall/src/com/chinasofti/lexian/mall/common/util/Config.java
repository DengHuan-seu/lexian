package com.chinasofti.lexian.mall.common.util;

import java.io.InputStream;
import java.util.Properties;

public class Config {
	public final static String VisitCountFilePath;
	public final static int CookieExpiredSeconds;
	public final static String PicServerVirtualPath;
	
	static
	{
		InputStream ins = Config.class.getResourceAsStream("/resource/config.properties");
		Properties p = new Properties();  
        try {  
            p.load(ins);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        PicServerVirtualPath = p.getProperty("picServerVirtualPath");
        VisitCountFilePath = p.getProperty("visitCountFilePath");
        CookieExpiredSeconds = Integer.valueOf(p.getProperty("cookieExpiredSeconds"));
	}
}
