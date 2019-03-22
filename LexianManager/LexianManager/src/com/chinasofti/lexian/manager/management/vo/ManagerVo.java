package com.chinasofti.lexian.manager.management.vo;

import java.util.Date;

import com.chinasofti.lexian.manager.common.util.PageHelper;
import com.chinasofti.lexian.manager.management.po.ManagerPo;

public class ManagerVo extends PageHelper<ManagerPo> {
	private int managerId;
	private String managerName;
	private String description;
	private Date createTime;
	private Date updateTime;
	private int status;
	private String roleIds;
	private String password;
	
	public ManagerVo(){
	}
	
	public ManagerVo(ManagerPo po){
		this.managerId = po.getId();
		this.managerName = po.getName();
		this.description = po.getInfo();
		this.createTime = po.getCreateTime();
		this.updateTime = po.getUpdateTime();
		this.status = po.getStatus();
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
	public int getStatus() {
		return status;
	}
	public String getStatusText() {
		switch(this.status){
		case 1:
			return "激活";
		default:
			return "冻结";
		}
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
