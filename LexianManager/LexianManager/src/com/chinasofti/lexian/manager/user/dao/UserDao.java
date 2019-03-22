package com.chinasofti.lexian.manager.user.dao;

import java.util.List;

import com.chinasofti.lexian.manager.user.po.UserPo;
import com.chinasofti.lexian.manager.user.vo.UpdateUserVo;
import com.chinasofti.lexian.manager.user.vo.UserVo;

public interface UserDao {
	public List<UserPo> findUsers(UserVo userVo);

	public int updateUserVo(UpdateUserVo updateUserVo);
}
