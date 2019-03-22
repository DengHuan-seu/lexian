package com.chinasofti.lexian.manager.management.vo;

import com.chinasofti.lexian.manager.common.util.PageHelper;
import com.chinasofti.lexian.manager.management.po.PrivilegePo;

public class PrivilegeVo extends PageHelper<PrivilegeVo>{
	private int id;
	private String url;
	private String privilegeName;
	private String description;
	private int roleId;
	
	public PrivilegeVo(){
	}
	
	public PrivilegeVo(PrivilegePo po){
		this.id = po.getId();
		this.url = po.getUrl();
		this.privilegeName = po.getName();
		this.description = po.getDescription();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
