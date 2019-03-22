package com.chinasofti.lexian.mall.common.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class VisitCounter implements InitializingBean{
	
	private final Logger logger = Logger.getLogger(getClass());
	
	private String visitKey="lexianVid";
	private String filePath = "";
	private int count = 200;
	private Ehcache ehcache;

	public void setVisitKey(String visitKey) {
		this.visitKey = visitKey;
	}
	
	public void setEhcache(Ehcache ehcache) {
		this.ehcache = ehcache;
	}
	
	public VisitCounter(){
		setEhcache(ApplicationContextUtil.getBean("sessionCache",Ehcache.class));
		filePath = com.chinasofti.lexian.mall.common.util.Config.VisitCountFilePath;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	private AtomicLong atomicLong;
	
	public AtomicLong getAtomicLong() {
		return atomicLong;
	}
	
	public synchronized long getCount(){
		return atomicLong.get();
	}
	
    public synchronized  long increaseAndgetCount(HttpServletRequest request,HttpServletResponse response){
    	String visitId = CommonUtil.getCookieValue(request.getCookies(), visitKey);
    	if(StringUtils.isNullOrEmpty(visitId)){
    		atomicLong.incrementAndGet();
    		visitId = UUID.randomUUID().toString();
			Cookie cookie3 = new Cookie(visitKey, visitId);
			cookie3.setPath("/");
			response.addCookie(cookie3);
			ehcache.put(new Element(visitId,visitId));
    	}
    	else{
    		Element element = ehcache.get(visitId);
    		if(null==element){
    			atomicLong.incrementAndGet();
        		visitId = UUID.randomUUID().toString();
    			Cookie cookie3 = new Cookie(visitKey, visitId);
    			response.addCookie(cookie3);
    			ehcache.put(new Element(visitId,visitId));
    		}
    	}
    	if(atomicLong.longValue() % count == 0){
    	  DataOutputStream dataOutputStream = null;
    	  try{
    		  dataOutputStream = new DataOutputStream(new FileOutputStream(filePath));
    		  dataOutputStream.writeLong(atomicLong.get());
    		  dataOutputStream.flush();
    	  }
    	  catch(Exception exception){
    		  logger.error("write file error:"+exception.getMessage(),exception);
    	  }
    	  finally{
    		  if(dataOutputStream!=null){
    			  try {
    				  dataOutputStream.close();
    			  } catch (IOException e) {
    				  e.printStackTrace();
    			  }
    			  dataOutputStream = null;
    		  }
    	  }
    	}
    	return atomicLong.get();
    }
	
	@Override
	public void afterPropertiesSet() throws Exception {
		File file = new File(filePath);
		atomicLong = new AtomicLong(0l);
		if(!file.exists()||!file.isFile()){
		    file.createNewFile();
			DataOutputStream  dataOutputStream = new DataOutputStream(new FileOutputStream(file));
			dataOutputStream.writeLong(atomicLong.get());
			dataOutputStream.flush();
			dataOutputStream.close();
			return;
		}
		
		DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
		long data = dataInputStream.readLong();
		atomicLong.set(data);
		dataInputStream.close();
	}
}
