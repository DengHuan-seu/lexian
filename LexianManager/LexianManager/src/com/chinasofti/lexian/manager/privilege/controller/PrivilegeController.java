package com.chinasofti.lexian.manager.privilege.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.manager.common.controller.BaseController;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil.ParamNotValidException;
import com.chinasofti.lexian.manager.privilege.constant.PrivilegeConstant;
import com.chinasofti.lexian.manager.privilege.po.LogPo;
import com.chinasofti.lexian.manager.privilege.service.PrivilegeService;
import com.chinasofti.lexian.manager.privilege.vo.LoginVo;
import com.chinasofti.lexian.manager.privilege.vo.ResetPassword;
import com.chinasofti.lexian.manager.privilege.vo.UserLogVo;

@Controller
@RequestMapping("/login")
public class PrivilegeController extends BaseController {
	private PrivilegeService privilegeService;
	
	@Autowired
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	// 用户登录
	@RequestMapping("/login.do")
	@ResponseBody
	public ResultHelper login(LoginVo loginVo, HttpServletRequest httpServletRequest) {
		try {
			ParamValidateUtil.validateNull(loginVo, PrivilegeConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(loginVo.getUserName(), PrivilegeConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(loginVo.getPassWord(), PrivilegeConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(Constant.failed_code, e.getMessage());
		}
		return privilegeService.login(loginVo,httpServletRequest);
	}

	//用户注销
	@RequestMapping("/logout.do")
	@ResponseBody
	public ResultHelper logout(HttpSession session) {
		session.removeAttribute("user");
		return new ResultHelper(Constant.success_code, PrivilegeConstant.success);
	}
    
	// 查询用户操作日志记录
	@RequestMapping("/readUserLog.do")
	@ResponseBody
	public ResultHelper readUserLog(UserLogVo userLogVo) {
		try {
			ParamValidateUtil.validateNull(userLogVo, PrivilegeConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(Constant.failed_code, e.getMessage());
		}
		return privilegeService.readUserLog(userLogVo);
	}
	
	@RequestMapping("/resetPassword.do")
	@ResponseBody
    public ResultHelper resetPassword(HttpSession session,@ModelAttribute ResetPassword resetPassword){
    	try {
    		ParamValidateUtil.validateEmpty(resetPassword.getOldPassword(), PrivilegeConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(resetPassword.getNewPassword(), PrivilegeConstant.invalid_arguments);
    	} catch (ParamNotValidException e) {
            return new ResultHelper(Constant.failed_code,e.getMessage());
		}
		return privilegeService.resetPassword(session, resetPassword);
    }
	


	@RequestMapping("/getLogs.do")
	@ResponseBody
	public ResultHelper getLogs(LogPo logPo){
		return privilegeService.getLogs(logPo);
	}
	
	@RequestMapping("/getSession.do")
	@ResponseBody
	public ResultHelper getSession(HttpServletRequest httpServletRequest){
		return privilegeService.getSession(httpServletRequest);
	}
	
}
