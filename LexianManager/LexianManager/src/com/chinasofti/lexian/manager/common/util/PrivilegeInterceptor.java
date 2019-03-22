package com.chinasofti.lexian.manager.common.util;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chinasofti.lexian.manager.common.controller.BaseController;
import com.chinasofti.lexian.manager.privilege.po.Record;
import com.chinasofti.lexian.manager.privilege.service.PrivilegeService;
import com.chinasofti.lexian.manager.privilege.vo.Administrator;

public class PrivilegeInterceptor extends BaseController implements HandlerInterceptor {

	private final String session_id = "user";
 
	private final String OPERATION_FAILED = "操作失败";
	private PrivilegeService PrivilegeService;

	@Autowired
	public void setPrivilegeService(PrivilegeService privilegeService) {
		PrivilegeService = privilegeService;
	}

	@SuppressWarnings("serial")
	@Override
	public void afterCompletion(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException)
					throws Exception {
		try {
			/**
			 * 参数类型绑定不对 提示客户端
			 */
			if (paramException instanceof BindException) {
				outputData(paramHttpServletRequest, paramHttpServletResponse, new HashMap<String, Object>() {
					{
						put("code", Constant.failed_code);
						put("msg", OPERATION_FAILED);
					}
				});
			}
		} catch (Exception e) {
			// logger.error(e);
		}
		
		if(!paramHttpServletRequest.getRequestURI().endsWith(".do")&&!paramHttpServletRequest.getRequestURI().endsWith(".html")){
			return;
		}
		if (paramException == null) {
			/**
			 * 操作没有异常 则记录操作日志
			 */
			HttpSession httpSession =  paramHttpServletRequest.getSession(false);
			if(httpSession==null){
				return;
			}
			
			Administrator administrator = (Administrator)httpSession.getAttribute(session_id);;
			if (administrator!=null) {
				String url = paramHttpServletRequest.getRequestURI();
				Record record = new Record();
				record.setManagerId(administrator.getUserid());
				record.setIp(CommonUtil.getClientIp(paramHttpServletRequest));
				record.setUrl(url);
				record.setCreateTime(new Date());
				PrivilegeService.addUserLog(record);
				return;
			}
			return;
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
