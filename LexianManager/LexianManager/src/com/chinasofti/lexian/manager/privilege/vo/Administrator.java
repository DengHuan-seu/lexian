package com.chinasofti.lexian.manager.privilege.vo;

import java.util.Date;
import java.util.List;

import com.chinasofti.lexian.manager.privilege.po.MenuPo;

public class Administrator {
	private int userid;
	private String userName;
	private List<String> role;
	private List<String> url;
	private List<MenuPo> menus;
	private List<String> roleId;
	private Date loginTime;

	public List<String> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<String> roleId) {
		this.roleId = roleId;
	}

	public void setMenus(List<MenuPo> menus) {
		this.menus = menus;
	}

	public List<MenuPo> getMenus() {
		return menus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}
