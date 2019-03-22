
package com.chinasofti.lexian.mall.common.util;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chinasofti.lexian.mall.common.controller.BaseController;
import com.chinasofti.lexian.mall.common.dao.BaseRedisDao;
import com.chinasofti.lexian.mall.user.po.User;

public class MvcInterceptor extends BaseController implements HandlerInterceptor {
	private BaseRedisDao baseRedisDao;
    
	public void setBaseRedisDao(BaseRedisDao baseRedisDao) {
		this.baseRedisDao = baseRedisDao;
	}

	private List<String> loginUrls;
	
	public void setLoginUrls(List<String> loginUrls) {
		this.loginUrls = loginUrls;
	}
	
	public List<String> getLoginUrls() {
		return loginUrls;
	}
	
	@SuppressWarnings("serial")
	@Override
	public void afterCompletion(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException)
					throws Exception {
		try {
			if (paramException instanceof BindException) {
				outputData(paramHttpServletRequest, paramHttpServletResponse, new HashMap<String, Object>() {
					{
						put("code", Constant.failed_code);
						put("msg", "参数错误");
					}
				});
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
		Object paramObject, ModelAndView paramModelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
			Object paramObject) throws Exception {
		String lexianKey = CommonUtil.getCookieValue(paramHttpServletRequest.getCookies(), Constant.LEXIANUSERKEY);
		final User user = baseRedisDao.getObject(lexianKey);
		String url = paramHttpServletRequest.getRequestURI();
		boolean loginNeeded = isUrlProtected(url) && null == user;
        if(loginNeeded){
        	String content = "{\"code\":\"3\",\"message\":\"对不起，您还没有登录,请先登录！\"}";
        	byte [] bodys = content.getBytes("UTF-8");
        	OutputStream outputStream = null;
        	try{
        		outputStream = paramHttpServletResponse.getOutputStream();
            	outputStream.write(bodys);
            	paramHttpServletResponse.setContentType("text/html;charset=UTF-8");
            	paramHttpServletResponse.setContentLength(bodys.length);
        	}
        	catch(Exception e){
        		logger.error(e.getMessage());
        	}
        	finally {
        		if(outputStream!=null)
				{
        			outputStream.flush();
				    outputStream.close();
				    paramHttpServletResponse.flushBuffer();
				    paramHttpServletResponse = null;
				}
			}
		}
        
        final int expires = com.chinasofti.lexian.mall.common.util.Config.CookieExpiredSeconds;
        if(null != user && baseRedisDao.expire(lexianKey, expires)){
        	paramHttpServletResponse.addCookie(
        			new Cookie(Constant.LEXIANUSERKEY, 
        					CommonUtil.getCookieValue(
        							paramHttpServletRequest.getCookies(),
        							Constant.LEXIANUSERKEY)){
    			private static final long serialVersionUID = 1L;
    			{
            		setMaxAge(expires);
            		setPath("/");
            	}
            });
            paramHttpServletRequest.setAttribute(Constant.LEXIANUSERKEY, user);
        }
		return !loginNeeded;
		
	}

	private boolean isUrlProtected(String url){
		if(null == loginUrls || loginUrls.size()==0)
			  return true;
		 for(String loginUrl:loginUrls){
			 if(loginUrl.contains(url))
				 return false;
		 }
		return true;
	}
}
