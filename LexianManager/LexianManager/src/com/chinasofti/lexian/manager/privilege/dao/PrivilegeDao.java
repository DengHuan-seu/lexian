package com.chinasofti.lexian.manager.privilege.dao;

import java.util.List;

import com.chinasofti.lexian.manager.privilege.po.LogPo;
import com.chinasofti.lexian.manager.privilege.po.MenuPo;
import com.chinasofti.lexian.manager.privilege.po.Record;
import com.chinasofti.lexian.manager.privilege.vo.LoginVo;
import com.chinasofti.lexian.manager.privilege.vo.ResetPassword;
import com.chinasofti.lexian.manager.privilege.vo.UserLogVo;

public interface PrivilegeDao {
	public List<String> getUrl(int userId);
	
	public List<MenuPo> getMenus(int userId); 
	
	public List<LogPo> getLogs(LogPo logPo); 
	
	public LoginVo login(LoginVo loginVo);

	public void addUserLog(Record record);
 
	public List<Record> readUserLog(UserLogVo userLogVo);
	 
	public List<String> findAllRole(String managerId);
	 
	public void resetPassword(ResetPassword resetPassword);
}
