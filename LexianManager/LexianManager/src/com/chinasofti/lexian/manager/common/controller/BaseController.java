package com.chinasofti.lexian.manager.common.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.chinasofti.lexian.manager.common.util.CommonUtil;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.gaotime.gip.commons.util.GzipHelp;

public class BaseController {
	protected Logger logger = Logger.getLogger(getClass());

	public void outputData(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		response.setContentType("text/html");
		byte[] bytes = null;
		if (null == object) {
			response.getOutputStream().close();
			return;
		}
		
		bytes = JSON.toJSONBytes(object, Constant.serializerFeatures);
		
		// 检查客户端是否支持gzip
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

	// 将request中的参数转成map的名值对
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

	// 判断是否支持某种压缩模式
	protected boolean acceptsEncoding(HttpServletRequest request, String name) {
		boolean accepts = headerContains(request, "Accept-Encoding", name);
		return accepts;
	}

	// 判断该Http头是否包含指定的值
	protected boolean headerContains(HttpServletRequest request, String header,
			String value) {

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

	// debug模式下打印http请求头以及对应的值
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
				logLine.append(": ").append(name).append(" -> ")
						.append(headerValue);
			}
			logger.debug(logLine.toString());
		}
	}


}
