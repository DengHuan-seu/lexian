package com.chinasofti.lexian.manager.privilege.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.manager.common.dao.BaseDao;
import com.chinasofti.lexian.manager.privilege.dao.PrivilegeDao;
import com.chinasofti.lexian.manager.privilege.po.LogPo;
import com.chinasofti.lexian.manager.privilege.po.MenuPo;
import com.chinasofti.lexian.manager.privilege.po.Record;
import com.chinasofti.lexian.manager.privilege.vo.LoginVo;
import com.chinasofti.lexian.manager.privilege.vo.ResetPassword;
import com.chinasofti.lexian.manager.privilege.vo.UserLogVo;

@Repository
public class PrivilegeDaoImpl extends BaseDao implements PrivilegeDao {
	@Override
	public LoginVo login(LoginVo loginVo) {
		return selectOne("login", loginVo);
	}

	@Override
	public List<String> getUrl(int userId) {
		return selectList("getUrl", userId);
	}
 
	@Override
	public void addUserLog(Record record) {
		insert("addUserLog", record);

	} 
	
	@Override
	public List<Record> readUserLog(UserLogVo userLogVo) {
		return selectList("readUserLog", userLogVo);
	}

	@Override
	public List<LogPo> getLogs(LogPo logPo) {
		return selectList("getLogs", logPo);
	}

	@Override
	public List<MenuPo> getMenus(int userId) {
		return selectList("getMenus",userId);
	}

	@Override
	public List<String> findAllRole(String managerId) {
		return selectList("findAllRole",managerId);
	}

	@Override
	public void resetPassword(ResetPassword resetPassword) {
		update("resetPassword",resetPassword);
	}
}
