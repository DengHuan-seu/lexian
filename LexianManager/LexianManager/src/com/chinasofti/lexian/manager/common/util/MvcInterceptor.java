package com.chinasofti.lexian.manager.common.util;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chinasofti.lexian.manager.common.controller.BaseController;

public class MvcInterceptor extends BaseController implements HandlerInterceptor {
	@SuppressWarnings("serial")
	@Override
	public void afterCompletion(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, 
			Object paramObject, 
			Exception paramException) throws Exception {
		try {
			// 参数类型绑定错误，提示客户端
			if (paramException instanceof BindException) {
				outputData(paramHttpServletRequest, paramHttpServletResponse, new HashMap<String, Object>() {
					{
						put("code", Constant.failed_code);
						put("msg", "操作失败");
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
		return true;
	}
}
