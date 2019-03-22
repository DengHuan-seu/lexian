package com.chinasofti.lexian.manager.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.manager.common.controller.BaseController;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil.ParamNotValidException;
import com.chinasofti.lexian.manager.user.constant.UserConstant;
import com.chinasofti.lexian.manager.user.service.UserService;
import com.chinasofti.lexian.manager.user.vo.UpdateUserVo;
import com.chinasofti.lexian.manager.user.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	// 分页查找会员数据
	@RequestMapping("findUsers.do")
	@ResponseBody
	public Object findUsers(UserVo userVo){
		return userService.findUsers(userVo);
	}
	
	// 更新会员信息
	@RequestMapping("updateUser.do")
	@ResponseBody
	public Object updateUserVo(@ModelAttribute UpdateUserVo udpateUser){
		
		try {
			if(udpateUser.getStatus()!=null){
				ParamValidateUtil.validatePositive(udpateUser.getStatus(), 0, 4, 
						UserConstant.invalid_arguments);
			}
			ParamValidateUtil.validateNull(udpateUser,UserConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(udpateUser.getId(), UserConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			logger.warn(e.getMessage());
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return userService.updateUserVo(udpateUser);
	}
}
