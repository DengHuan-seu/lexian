package com.chinasofti.lexian.manager.management.vo;

import java.util.ArrayList;
import java.util.List;

import com.chinasofti.lexian.manager.management.po.RoleMenuPo;

public class RoleMenuVo {
	private int menuId;
	private String menuName;
	private Boolean checked;
	private List<RoleMenuVo> subMenus;
	
	public RoleMenuVo(){
		this.subMenus = new ArrayList<RoleMenuVo>();
	}
	
	public RoleMenuVo(RoleMenuPo po){
		this.menuId = po.getId();
		this.menuName = po.getName();
		this.checked = po.getRole_id() > 0 ? true : false;
		this.subMenus = new ArrayList<RoleMenuVo>();
	}
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public List<RoleMenuVo> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<RoleMenuVo> subMenus) {
		this.subMenus = subMenus;
	}
}
