package com.chinasofti.lexian.manager.management.service.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.management.constant.ManagementConstant;
import com.chinasofti.lexian.manager.management.dao.ManagementDao;
import com.chinasofti.lexian.manager.management.po.PrivilegePo;
import com.chinasofti.lexian.manager.management.po.RoleMenuPo;
import com.chinasofti.lexian.manager.management.po.RolePo;
import com.chinasofti.lexian.manager.management.po.RolePrivilegePo;
import com.chinasofti.lexian.manager.management.service.ManagementService;
import com.chinasofti.lexian.manager.management.vo.PrivilegeVo;
import com.chinasofti.lexian.manager.management.vo.RoleMenuVo;
import com.chinasofti.lexian.manager.management.vo.ManagerVo;
import com.chinasofti.lexian.manager.management.vo.MenuVo;
import com.chinasofti.lexian.manager.management.vo.RoleVo;
import com.chinasofti.lexian.manager.management.po.ManagerPo;
import com.chinasofti.lexian.manager.management.po.ManagerRolePo;
import com.chinasofti.lexian.manager.management.po.MenuPo;

@Service
@Transactional
public class ManagementServiceImpl extends BaseService implements ManagementService {
	private ManagementDao managementDao;

	@Autowired
	public void setManagementDao(ManagementDao managementDao) {
		this.managementDao = managementDao;
	}
	
	@Override
	public ResultHelper findPrivileges(PrivilegeVo privilegeVo) {
		List<PrivilegePo> pos = managementDao.findPrivileges(privilegeVo);
		List<PrivilegeVo> vos = new ArrayList<PrivilegeVo>();
		for(PrivilegePo po : pos){
			PrivilegeVo vo = new PrivilegeVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, ManagementConstant.success, 
				vos, privilegeVo.getTotal());
	}
	
	@Override
	public ResultHelper getMenus(MenuVo menuVo) {
         List<MenuPo> pos =  managementDao.findMenus(menuVo);
         List<MenuVo> vos = new ArrayList<MenuVo>();
         for(MenuPo po : pos){
        	 MenuVo vo = new MenuVo(po);
        	 vos.add(vo);
         }
         return new ResultHelper(Constant.success_code, ManagementConstant.success, 
        		 vos, menuVo.getTotal());
	}
	
	@Override
	public ResultHelper updateMenu(MenuVo menuVo) {
		String url = null;
		if(menuVo.getFile()!=null && menuVo.getFile().getSize()>0){
			url = saveImage("",getServletRealPath("/img"), menuVo.getFile());
		}
		
		MenuPo menuPo = new MenuPo();
		menuPo.setId(menuVo.getMenuId());
		menuPo.setName(menuVo.getMenuName());
		menuPo.setBackUrl(url);
		managementDao.updateMenu(menuPo);
		
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	
	@Override
	public ResultHelper findRoles(RoleVo roleVo) {
		List<RolePo> pos =  managementDao.findRoles(roleVo);
        List<RoleVo> vos = new ArrayList<RoleVo>();
        for(RolePo po : pos){
       	 RoleVo vo = new RoleVo(po);
       	 vos.add(vo);
        }
        return new ResultHelper(Constant.success_code, ManagementConstant.success, 
       		 vos, roleVo.getTotal());
	}
	@Override
	public ResultHelper updateRole(RoleVo roleVo) {
		RolePo existedRole = managementDao.findRoleByName(roleVo.getRoleName());
		if (existedRole != null && existedRole.getId() != roleVo.getRoleId()) {
			return new ResultHelper(Constant.success_code, ManagementConstant.duplicate_rolename);
		}
		
		RolePo rolepo = new RolePo();
		rolepo.setId(roleVo.getRoleId());
		rolepo.setName(roleVo.getRoleName());
		rolepo.setDescription(roleVo.getDescription());
		managementDao.updateRole(rolepo);
		
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	
	@Override
	public ResultHelper addRole(RoleVo roleVo) {
		RolePo existedRole = managementDao.findRoleByName(roleVo.getRoleName());
		if (existedRole != null) {
			return new ResultHelper(Constant.success_code, ManagementConstant.duplicate_rolename);
		}
		
		RolePo rolepo = new RolePo();
		rolepo.setId(roleVo.getRoleId());
		rolepo.setName(roleVo.getRoleName());
		rolepo.setDescription(roleVo.getDescription());
		managementDao.addRole(rolepo);
		
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	@Override
	public ResultHelper findRolePrivileges(PrivilegeVo privilegeVo) {
		privilegeVo.setRows(Integer.MAX_VALUE);		// 不分页
		List<PrivilegePo> pos = managementDao.findPrivileges(privilegeVo);
		List<PrivilegeVo> vos = new ArrayList<PrivilegeVo>();
		for(PrivilegePo po : pos){
			PrivilegeVo vo = new PrivilegeVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, ManagementConstant.success, vos);
	}
	
	@Override
	public ResultHelper changeRolePrivileges(int roleId, String privilegeIds) {
		managementDao.deletePrivilegeInRole(roleId);
		addPrivilegeToRole(roleId, privilegeIds);
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	
	@Override
	public ResultHelper addPrivilegeToRole(int roleId, String privilegeIds) {
		String[] arr = privilegeIds.split(",");
		RolePrivilegePo rp = new RolePrivilegePo();
		rp.setRole_id(roleId);
		for (String str : arr) {
			rp.setPrivilege_id(Integer.parseInt(str));
			managementDao.addPrivilegeToRole(rp);
		}
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	@Override
	public ResultHelper findRoleMenus(int roleId) {
		List<RoleMenuPo> pos = managementDao.findRoleMenus(roleId);
		List<RoleMenuVo> vos = new ArrayList<RoleMenuVo>();
		Map<Integer, RoleMenuVo> map = new HashMap<Integer, RoleMenuVo>();
		for(RoleMenuPo po : pos){
			if(po.getParentId() == 0){			// 父菜单
				RoleMenuVo parentVo = new RoleMenuVo(po);
				map.put(po.getId(), parentVo);
				vos.add(parentVo);
			} else{								// 子菜单
				RoleMenuVo childVo = new RoleMenuVo(po);
				RoleMenuVo parentVo = map.get(po.getParentId());
				parentVo.getSubMenus().add(childVo);
			}
		}
		return new ResultHelper(Constant.success_code, ManagementConstant.success, vos);
	}
	
	@Override
	public ResultHelper changeRoleMenus(final int roleId, final String menuIds) {
		managementDao.deleteRoleMenus(roleId);
		managementDao.addRolesMenus(new HashMap<String,Object>(){
			{
				put("roleId", roleId);
				put("menuIds", menuIds.split(","));
			}
		});
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	
	@Override
	public ResultHelper findManagers(ManagerVo managerVo) {
		List<ManagerPo> pos = managementDao.findManagers(managerVo);
		List<ManagerVo> vos = new ArrayList<ManagerVo>();
		for(ManagerPo po : pos){
			ManagerVo vo = new ManagerVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, ManagementConstant.success, 
				vos, managerVo.getTotal());
	}

	@Override
	public ResultHelper addManager(ManagerVo managerVo) {
		ManagerPo po = new ManagerPo();
		po.setName(managerVo.getManagerName());
		po.setPassword(managerVo.getPassword());
		po.setInfo(managerVo.getDescription());
		// 添加后台用户
		managementDao.addManager(po);
		// 为后台用户关联角色
		String[] str = managerVo.getRoleIds().split(",");
		ManagerRolePo mr = new ManagerRolePo();
		mr.setManager_id(po.getId());
		for (String string : str) {
			mr.setRole_id(Integer.valueOf(string));
			managementDao.addManagerRole(mr);
		}
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	@Override
	public ResultHelper updateManager(ManagerVo managerVo) {
		ManagerPo po = new ManagerPo();
		po.setId(managerVo.getManagerId());
		po.setInfo(managerVo.getDescription());
		managementDao.updateManager(po);
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	@Override
	public ResultHelper updateManagerRoles(int managerId, String roleIds) {
		managementDao.deleteManagerRoles(managerId);
		String[] str = roleIds.split(",");
		ManagerRolePo managerRole = new ManagerRolePo();
		managerRole.setManager_id(managerId);
		for (String string : str) {
			managerRole.setRole_id(Integer.valueOf(string));
			managementDao.addManagerRole(managerRole);
		}
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	@Override
	public ResultHelper findManagerRoles(int managerId) {
		List<RolePo> pos = managementDao.findManagerRoles(managerId);
		List<RoleVo> vos = new ArrayList<RoleVo>();
		for(RolePo po : pos){
			RoleVo vo = new RoleVo();
			vo.setRoleId(po.getId());
			vo.setRoleName(po.getName());
			vo.setDescription(po.getDescription());
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, ManagementConstant.success, vos);
	}
	
	@Override
	public ResultHelper deleteManagement(int managerId) {
		managementDao.deleteManagerRoles(managerId);
		managementDao.deleteManager(managerId);
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}

	@Override
	public ResultHelper updateStateToActivate(int managerId) {
		ManagerPo po = new ManagerPo();
		po.setId(managerId);
		po.setStatus(1);
		managementDao.updateManagerState(po);
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}

	@Override
	public ResultHelper updateStateToLogoff(int managerId) {
		ManagerPo po = new ManagerPo();
		po.setId(managerId);
		po.setStatus(0);
		managementDao.updateManagerState(po);
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
	
	public ResultHelper resetPassword(int managerId){
		ManagerPo po = new ManagerPo();
		po.setId(managerId);
		po.setPassword("123456");
		managementDao.resetPassword(po);
		return new ResultHelper(Constant.success_code, ManagementConstant.success);
	}
}
