package com.chinasofti.lexian.manager.management.dao;

import java.util.HashMap;
import java.util.List;

import com.chinasofti.lexian.manager.management.po.PrivilegePo;
import com.chinasofti.lexian.manager.management.po.RoleMenuPo;
import com.chinasofti.lexian.manager.management.po.RolePo;
import com.chinasofti.lexian.manager.management.po.RolePrivilegePo;
import com.chinasofti.lexian.manager.management.vo.ManagerVo;
import com.chinasofti.lexian.manager.management.vo.MenuVo;
import com.chinasofti.lexian.manager.management.vo.PrivilegeVo;
import com.chinasofti.lexian.manager.management.vo.RoleVo;
import com.chinasofti.lexian.manager.management.po.ManagerPo;
import com.chinasofti.lexian.manager.management.po.ManagerRolePo;
import com.chinasofti.lexian.manager.management.po.MenuPo;

public interface ManagementDao {
	public List<PrivilegePo> findPrivileges(PrivilegeVo privilegeVo);
	
	public List<MenuPo> findMenus(MenuVo menuVo);
	
	public void updateMenu(MenuPo menuPo);
	
	public List<RolePo> findRoles(RoleVo roleVo);
	
	public RolePo findRoleByName(String name);

	public void updateRole(RolePo rolepo);

	public void addRole(RolePo rolepo);

	public void deletePrivilegeInRole(int roleId);

	public void addPrivilegeToRole(RolePrivilegePo rp);

	public List<RoleMenuPo> findRoleMenus(int roleId);

	public void deleteRoleMenus(int roleId);

	public void addRolesMenus(HashMap<String, Object> map);

	public List<ManagerPo> findManagers(ManagerVo managerVo);

	public void addManager(ManagerPo po);

	public void addManagerRole(ManagerRolePo mr);

	public void updateManager(ManagerPo po);

	public void deleteManagerRoles(int managerId);

	public List<RolePo> findManagerRoles(int managerId);

	public void deleteManager(int managerId);

	public void updateManagerState(ManagerPo po);

	public void resetPassword(ManagerPo po);
}
