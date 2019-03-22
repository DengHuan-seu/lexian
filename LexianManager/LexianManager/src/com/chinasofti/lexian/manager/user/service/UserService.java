package com.chinasofti.lexian.manager.user.service;

import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.user.vo.UpdateUserVo;
import com.chinasofti.lexian.manager.user.vo.UserVo;

public interface UserService {
	public ResultHelper findUsers(UserVo userVo);

	public ResultHelper updateUserVo(UpdateUserVo udpateUser);
}
