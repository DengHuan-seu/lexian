package com.chinasofti.lexian.manager.management.vo;

import java.util.Date;

import com.chinasofti.lexian.manager.common.util.PageHelper;
import com.chinasofti.lexian.manager.management.po.RolePo;

public class RoleVo extends PageHelper<RoleVo>{
	private int roleId;
	private String roleName;
	private String description;
	private Date createTime;
	private Date updateTime;
	
	public RoleVo(){
	}
	
	public RoleVo(RolePo po){
		this.roleId = po.getId();
		this.roleName = po.getName();
		this.description = po.getDescription();
		this.createTime = po.getCreateTime();
		this.updateTime = po.getUpdateTime();
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int id) {
		this.roleId = id;
	}
	public String getRoleName() {
		return this.roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
