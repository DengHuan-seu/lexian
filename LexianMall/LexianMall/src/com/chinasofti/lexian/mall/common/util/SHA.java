package com.chinasofti.lexian.mall.common.util;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public enum SHA {
	instance;
	private MessageDigest  messageDigest;
	
	private Logger logger =Logger.getLogger(getClass());
	
	private SHA(){
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
    public  String getEncryptResult(String inStr) {
        String outStr = null;
        byte[] digest = messageDigest.digest(inStr.getBytes());    
        outStr = bytetoString(digest);
        return outStr;
    }
    
    private String bytetoString(byte[] digest) {
        StringBuilder str = new StringBuilder();
        String tempStr = "";
        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 0) {
                str.append(str).append("0");
            }
                str.append(tempStr);
        }
        return str.toString().toLowerCase();
    }
    
    public static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }
   
}