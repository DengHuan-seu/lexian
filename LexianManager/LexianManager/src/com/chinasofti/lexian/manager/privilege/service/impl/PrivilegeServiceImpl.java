package com.chinasofti.lexian.manager.privilege.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.privilege.constant.PrivilegeConstant;
import com.chinasofti.lexian.manager.privilege.dao.PrivilegeDao;
import com.chinasofti.lexian.manager.privilege.po.LogPo;
import com.chinasofti.lexian.manager.privilege.po.MenuPo;
import com.chinasofti.lexian.manager.privilege.po.Record;
import com.chinasofti.lexian.manager.privilege.service.PrivilegeService;
import com.chinasofti.lexian.manager.privilege.vo.Administrator;
import com.chinasofti.lexian.manager.privilege.vo.LoginVo;
import com.chinasofti.lexian.manager.privilege.vo.ResetPassword;
import com.chinasofti.lexian.manager.privilege.vo.UserLogVo;

@Service
@Transactional
public class PrivilegeServiceImpl extends BaseService implements PrivilegeService {
	private PrivilegeDao privilegeDao;

	@Autowired
	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	// 查出该用户的所有能访问的url地址
    private  List<String> getUrl(int userId) {
		return privilegeDao.getUrl(userId);
	}
   
    // 查出该用户所有能访问的菜单项
	private List<MenuPo>  getMenus(int userId) {
		return privilegeDao.getMenus(userId);
	}

	@Override
	public ResultHelper login(LoginVo loginVo,HttpServletRequest httpServletRequest) {
		// 检查用户名和密码是否匹配
		LoginVo loginInfo = privilegeDao.login(loginVo);
		if (loginInfo == null) {
			return new ResultHelper(Constant.failed_code, PrivilegeConstant.failed_login);
		}
		if (loginInfo.getStatus() == 2) { // 用户被禁止使用
			return new ResultHelper(Constant.failed_code, PrivilegeConstant.login_disabled);
		}
		// 获取该用户所属的所有角色
		List<String> roleIds = privilegeDao.findAllRole(String.valueOf(loginInfo.getUserId()));
		// 获取该用户所有能访问的url地址列表
		List<String> urList = getUrl(loginInfo.getUserId());
		
		Administrator administratorInfo = new Administrator();
		administratorInfo.setUrl(urList);
		administratorInfo.setMenus(getMenus(loginInfo.getUserId()));
		administratorInfo.setUserid(loginInfo.getUserId());
		administratorInfo.setUserName(loginInfo.getUserName());
		administratorInfo.setRoleId(roleIds);

		HttpSession session = httpServletRequest.getSession(true);
		session.setAttribute("user", administratorInfo);
		loginInfo.setLogTime(new Date());
		// 不要在对象中存储密码信息
		loginInfo.setPassWord(null);
		return new ResultHelper(Constant.success_code, PrivilegeConstant.success, 
				loginInfo);
	}

	@Override
	public void addUserLog(Record record) {
		privilegeDao.addUserLog(record);
	}

	@Override
	public ResultHelper readUserLog(UserLogVo userLogVo) {
		return new ResultHelper(Constant.success_code, PrivilegeConstant.success,
				privilegeDao.readUserLog(userLogVo),userLogVo.getTotal());
	}

	@Override
	public ResultHelper getLogs(LogPo logPo) {
		return new ResultHelper(Constant.success_code, PrivilegeConstant.success,
				privilegeDao.getLogs(logPo), logPo.getTotal());
	}

	@Override
	public ResultHelper getSession(HttpServletRequest httpServletRequest) {
		Administrator administratorInfo = (Administrator)httpServletRequest.getSession().getAttribute("user");
		if(administratorInfo == null){
			return new ResultHelper(Constant.failed_code, PrivilegeConstant.invalid_user);
		}
		administratorInfo.setLoginTime(new Timestamp(System.currentTimeMillis()));
		return new ResultHelper(Constant.success_code, PrivilegeConstant.success, administratorInfo);
	}

	@Override
	public ResultHelper resetPassword(HttpSession session,ResetPassword resetPassword) {
		Administrator administrator = (Administrator)session.getAttribute("user");
		if (administrator==null) {
			return new ResultHelper(Constant.failed_code, PrivilegeConstant.invalid_user);
		}
		LoginVo loginVo=new LoginVo();
		loginVo.setUserName(administrator.getUserName());
		loginVo.setPassWord(resetPassword.getOldPassword());
		LoginVo userinfo=privilegeDao.login(loginVo);
	    if (null == userinfo) {
		return new ResultHelper(Constant.failed_code, PrivilegeConstant.invalid_password);
	    }
	    resetPassword.setId(administrator.getUserid());
	    privilegeDao.resetPassword(resetPassword);
	    session.removeAttribute("user");
	    
		return new ResultHelper(Constant.success_code, PrivilegeConstant.success);
	}

}
