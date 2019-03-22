package com.chinasofti.lexian.mall.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chinasofti.lexian.mall.common.util.CommonUtil;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.user.po.User;
import com.gaotime.gip.commons.util.GzipHelp;


public class BaseController {
	protected Logger logger = Logger.getLogger(getClass());

	// 输出JSON字符串
	public void outputData(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		response.setContentType("text/html");
		byte[] bytes = null;
		if (null == object) {
			response.getOutputStream().close();
			return;
		}

		
		
		bytes = JSON.toJSONBytes(object, serializerFeatures);
		// 如果客户端支持gzip
		if (acceptsEncoding(request, "gzip")) {
			response.setHeader("Content-Encoding", "gzip");
			bytes = GzipHelp.compressBytes(bytes);
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} else {
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}

	// 将request中的参数转成map
	public Map<String, String> getRequestMap(HttpServletRequest request) {
		Map<String, String> resultMap = new HashMap<String, String>();
		Map<String, String[]> map = request.getParameterMap();
		if (map == null || map.size() == 0)
			return resultMap;
		Set<String> set = map.keySet();
		for (String key : set) {
			String[] value = map.get(key);
			resultMap.put(key, CommonUtil.joinList(",", value));
		}
		return resultMap;
	}

	protected boolean acceptsEncoding(HttpServletRequest request, String name) {
		boolean accepts = headerContains(request, "Accept-Encoding", name);
		return accepts;
	}

	protected boolean headerContains(HttpServletRequest request, String header, String value) {

		Enumeration<String> accepted = request.getHeaders(header);
		if (accepted == null)
			return false;
		while (accepted.hasMoreElements()) {
			String headerValue = (String) accepted.nextElement();
			if (headerValue.indexOf(value) != -1) {
				return true;
			}
		}
		return false;
	}

	// debug模式下打印http所有的头以及对应的值
	protected void logRequestHeaders(HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			Map<String, String> headers = new HashMap<String, String>();
			Enumeration<String> enumeration = request.getHeaderNames();
			StringBuffer logLine = new StringBuffer();
			logLine.append("Request Headers");
			while (enumeration.hasMoreElements()) {
				String name = (String) enumeration.nextElement();
				String headerValue = request.getHeader(name);
				headers.put(name, headerValue);
				logLine.append(": ").append(name).append(" -> ").append(headerValue);
			}
			logger.debug(logLine.toString());
		}
	}
	
	public String getAndLogHttpBody(HttpServletRequest request) {
		StringBuilder body = new StringBuilder();
		BufferedReader bf =null;
		try{
			 bf = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
				if(null!=bf){
					String line = null;
					while(null!=(line=bf.readLine())){
						body.append(line).append("\n");
					}
				}
				logger.info(body.toString());
		}
		catch(Exception e){
			logger.info("io exception:"+e.getMessage());
		}
		finally{
			if(null!=bf){
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				bf = null;
			}
		}
		return body.toString();
	}

	// 获取当前登录用户
	protected User getUser() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return  (User)request.getAttribute(Constant.LEXIANUSERKEY);
	}
	
	public static final SerializerFeature[] serializerFeatures = { SerializerFeature.QuoteFieldNames,
			SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteDateUseDateFormat,
			SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.SkipTransientField ,SerializerFeature.DisableCircularReferenceDetect};
}
