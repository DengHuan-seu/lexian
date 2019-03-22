package com.chinasofti.lexian.mall.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.common.dao.BaseDao;
import com.chinasofti.lexian.mall.user.dao.UserDao;
import com.chinasofti.lexian.mall.user.po.User;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
	@Override
	public User getUser(User user) {
		return selectOne("getUser", user);
	}

	@Override
	public int saveUser(User user) {
		return insert("saveUser", user);
	}
	
	@Override
	public int updateUser(User user) {
		return update("updateUser", user);
	}

	@Override
	public List<User> getUserList(User user) {
		return selectList("getUser", user);
	}
}
