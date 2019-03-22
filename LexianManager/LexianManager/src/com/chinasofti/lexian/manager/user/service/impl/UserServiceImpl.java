package com.chinasofti.lexian.manager.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.common.util.SHA;
import com.chinasofti.lexian.manager.user.constant.UserConstant;
import com.chinasofti.lexian.manager.user.dao.UserDao;
import com.chinasofti.lexian.manager.user.service.UserService;
import com.chinasofti.lexian.manager.user.vo.UpdateUserVo;
import com.chinasofti.lexian.manager.user.vo.UserVo;

@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public ResultHelper findUsers(UserVo userVo) {
		return new ResultHelper(Constant.success_code, UserConstant.success, 
				userDao.findUsers(userVo), userVo.getTotal());
	}

	@Override
	public ResultHelper updateUserVo(UpdateUserVo udpateUser) {
		if (udpateUser.getPassword() != null && udpateUser.getPassword() != "") {
			udpateUser.setPassword(SHA.instance.getEncryptResult(udpateUser.getPassword()));
		}
		userDao.updateUserVo(udpateUser);
		return new ResultHelper(Constant.success_code, UserConstant.success);
	}
}
