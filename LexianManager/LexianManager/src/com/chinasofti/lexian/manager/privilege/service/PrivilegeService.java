package com.chinasofti.lexian.manager.privilege.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.privilege.po.LogPo;
import com.chinasofti.lexian.manager.privilege.po.Record;
import com.chinasofti.lexian.manager.privilege.vo.LoginVo;
import com.chinasofti.lexian.manager.privilege.vo.ResetPassword;
import com.chinasofti.lexian.manager.privilege.vo.UserLogVo;

public interface PrivilegeService {
	public ResultHelper login(LoginVo loginVo,HttpServletRequest httpServletRequest);
	
	public void addUserLog(Record record);
	
	public ResultHelper readUserLog(UserLogVo userLogVo);
	
	public ResultHelper getLogs(LogPo logPo);
	
	public ResultHelper getSession(HttpServletRequest httpServletRequest);
    
	public ResultHelper resetPassword(HttpSession session,ResetPassword resetPassword);
}
