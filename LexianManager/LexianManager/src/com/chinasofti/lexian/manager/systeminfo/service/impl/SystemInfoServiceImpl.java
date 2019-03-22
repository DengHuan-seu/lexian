package com.chinasofti.lexian.manager.systeminfo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.chinasofti.lexian.manager.common.service.BaseService;

@Service
public class SystemInfoServiceImpl extends BaseService {
	public Map<Object,Object> getSystemInfo(final String ...keys){
       if(keys==null||keys.length==0)
    	   return System.getProperties();
       return new HashMap<Object,Object>(){
    	   private static final long serialVersionUID = 1L;
    	   {
    		   for(String key:keys){
    			   put(key,System.getProperty(key));
    		   }
    	   }
       };
	};
}
