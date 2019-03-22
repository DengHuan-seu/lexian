package com.chinasofti.lexian.manager.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.manager.common.dao.BaseDao;
import com.chinasofti.lexian.manager.user.dao.UserDao;
import com.chinasofti.lexian.manager.user.po.UserPo;
import com.chinasofti.lexian.manager.user.vo.UpdateUserVo;
import com.chinasofti.lexian.manager.user.vo.UserVo;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
	@Override
	public List<UserPo> findUsers(UserVo userVo) {
		return selectList("findUsers", userVo);
	}

	@Override
	public int updateUserVo(UpdateUserVo updateUserVo) {
		return update("updateUser", updateUserVo);
	}
}
