package com.chinasofti.lexian.mall.user.dao;

import java.util.List;

import com.chinasofti.lexian.mall.user.po.User;

public interface UserDao {
	public User getUser(User user);
	
	public List<User> getUserList(User user);

	public int saveUser(User user);
	
	public int updateUser(User user);
}
