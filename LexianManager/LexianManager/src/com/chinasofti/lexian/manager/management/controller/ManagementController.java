package com.chinasofti.lexian.manager.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.manager.common.controller.BaseController;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil.ParamNotValidException;
import com.chinasofti.lexian.manager.management.constant.ManagementConstant;
import com.chinasofti.lexian.manager.management.service.ManagementService;
import com.chinasofti.lexian.manager.management.vo.PrivilegeVo;
import com.chinasofti.lexian.manager.management.vo.ManagerVo;
import com.chinasofti.lexian.manager.management.vo.MenuVo;
import com.chinasofti.lexian.manager.management.vo.RoleVo;

@Controller
@RequestMapping("/management")
public class ManagementController extends BaseController {

	private ManagementService managementService;

	@Autowired
	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}
	
	// 以分页的方式查询系统权限列表
	@RequestMapping("/findPrivileges.do")
	@ResponseBody
	public Object findPrivileges(PrivilegeVo privilegeVo) {
		return managementService.findPrivileges(privilegeVo);
	}
	
	// 分页查询返回所有菜单
	@RequestMapping("/findMenus.do")
	@ResponseBody
	public ResultHelper findMenus(MenuVo menuVo){
		return managementService.getMenus(menuVo);
	}
	
	// 修改菜单基本信息
	@RequestMapping("/updateMenu.do")
	@ResponseBody
	public ResultHelper updateMenu(MenuVo menuVo){
		try{
			ParamValidateUtil.validatePositive(menuVo.getMenuId(), ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(menuVo.getMenuName(),ManagementConstant.invalid_arguments);
		}catch(ParamNotValidException exception){
			return new ResultHelper(exception.getCode(),exception.getMessage());
		}
		return managementService.updateMenu(menuVo);
	}
	
	// 分页查询角色信息
	@RequestMapping("/findRoles.do")
	@ResponseBody
	public Object findRoles(RoleVo roleVo){
 		return managementService.findRoles(roleVo);
	}
	
	// 修改角色信息
	@RequestMapping("/updateRole.do")
	@ResponseBody
	public Object updateRole(RoleVo rolevo) {
		try {
			ParamValidateUtil.validateNull(rolevo, ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(rolevo.getRoleName(), ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(rolevo.getRoleName(), 50, ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(rolevo.getDescription(), ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(rolevo.getDescription(), 200, ManagementConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return managementService.updateRole(rolevo);
	}
	
	// 添加角色
	@RequestMapping("/addRole.do")
	@ResponseBody
	public Object addRole(RoleVo rolevo) {
		try {
			ParamValidateUtil.validateEmpty(rolevo.getRoleName(), ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(rolevo.getRoleName(), 50, ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(rolevo.getDescription(), ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(rolevo.getDescription(), 200, ManagementConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return managementService.addRole(rolevo);
	}
	
	// 获取某个角色对应的权限
	@RequestMapping("/findRolePrivileges.do")
	@ResponseBody
	public Object findRolePrivileges(PrivilegeVo privilegeVo) {
		return managementService.findRolePrivileges(privilegeVo);
	}
	
	// 更改角色权限
	@RequestMapping("/changeRolePrivileges.do")
	@ResponseBody
	public Object changeRolePrivileges(int roleId, String privilegeIds) {
		try {
			ParamValidateUtil.validateEmpty(roleId, ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(privilegeIds, ManagementConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return managementService.changeRolePrivileges(roleId, privilegeIds);
	}
	
	// 获取某个角色对应的菜单
	@RequestMapping("/findRoleMenus.do")
	@ResponseBody
	public Object findRoleMenus(int roleId){
		return managementService.findRoleMenus(roleId);
	}
	
	// 向某个角色添加可访问的菜单项
    @RequestMapping("/changeRoleMenus.do")
    @ResponseBody
    public Object changeRoleMenus(int roleId, String menuIds){
	    return managementService.changeRoleMenus(roleId, menuIds);
    }
	
	// 分页查找后台用户
	@RequestMapping("/findManagers.do")
	@ResponseBody
	public Object findManagers(ManagerVo managerVo) {
		return managementService.findManagers(managerVo);
	}
	
	// 添加后台用户
	@RequestMapping("/addManager.do")
	@ResponseBody
	public Object addManager(ManagerVo managerVo) {
		try {
			ParamValidateUtil.validateNull(managerVo.getRoleIds(),ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(managerVo.getManagerName(), ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(managerVo.getPassword(), ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(managerVo.getManagerName(), 20, ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(managerVo.getPassword(), 40, ManagementConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return managementService.addManager(managerVo);
	}
	
	// 更新后台用户基本信息
	@RequestMapping("/updateManager.do")
	@ResponseBody
	public Object updateManager(ManagerVo managerVo) {
		try {
			ParamValidateUtil.validateEmpty(managerVo.getDescription(), ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(managerVo.getManagerId(), ManagementConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(managerVo.getDescription(), 200, ManagementConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return managementService.updateManager(managerVo);
	}
	
	// 将角色与后台用户进行关联
	@ResponseBody
	@RequestMapping("/updateManagerRole.do")
	public Object updateManagerRoles(int managerId, String roleIds){
		return managementService.updateManagerRoles(managerId, roleIds);
	}
	
	// 查找某后台用户所属的角色
	@RequestMapping("/findManagerRoles.do")
	@ResponseBody
	public Object findManagerRoles(int managerId) {
		try {
			ParamValidateUtil.validateEmpty(managerId, ManagementConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return managementService.findManagerRoles(managerId);
	}
	

	// 删除后台用户
	@RequestMapping("/deleteManager.do")
	@ResponseBody
	public Object deleteManager(int managerId) {

		try {
			ParamValidateUtil.validateEmpty(managerId, ManagementConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return managementService.deleteManagement(managerId);
	}

	// 禁用后台用户
	@RequestMapping("/disableManager.do")
	@ResponseBody
	public Object disableManager(int managerId) {
		try {
			ParamValidateUtil.validateEmpty(managerId, ManagementConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return managementService.updateStateToLogoff(managerId);
	}
	
	// 激活后台用户
	@RequestMapping("/activateManager.do")
	@ResponseBody
	public Object activateManager(int managerId) {
		try {
			ParamValidateUtil.validateEmpty(managerId, ManagementConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return managementService.updateStateToActivate(managerId);
	}

	// 重置后台用户密码
	@RequestMapping("/resetPassword.do")
	@ResponseBody 
	public ResultHelper resetPassword(int managerId){
		try {
			ParamValidateUtil.validateEmpty(managerId, ManagementConstant.success);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(),e.getMessage());
		}
		return managementService.resetPassword(managerId);
	}
	

//    // 验证码
//    @RequestMapping("/verification.do")
//	@ResponseBody
//	public Object verification(@ModelAttribute Verification verification){
//		try {
//			ParamValidateUtil.validateEmpty(verification.getType(),"type不能为空");
//		} catch (ParamNotValidException e) {
//        return new ResultHelper(e.getCode(),e.getMessage());
//		}
//		return managementService.verification(verification);
//	}
}
