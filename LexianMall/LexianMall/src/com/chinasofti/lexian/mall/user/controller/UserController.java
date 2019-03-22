package com.chinasofti.lexian.mall.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinasofti.lexian.mall.common.controller.BaseController;
import com.chinasofti.lexian.mall.common.util.CommonUtil;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.ParamValidateUtil;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.common.util.VisitCounter;
import com.chinasofti.lexian.mall.common.util.ParamValidateUtil.ParamNotValidException;
import com.chinasofti.lexian.mall.user.constant.UserConstant;
import com.chinasofti.lexian.mall.user.service.UserService;
import com.chinasofti.lexian.mall.user.vo.ChangePasswordVo;
import com.chinasofti.lexian.mall.user.vo.LoginVo;
import com.chinasofti.lexian.mall.user.vo.ResetPasswordVo;
import com.chinasofti.lexian.mall.user.vo.UserVo;
import com.chinasofti.lexian.mall.user.vo.RegisterVo;
import com.chinasofti.lexian.mall.validation.constant.ValidateConstant;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private UserService userService;

	@Autowired
	private VisitCounter visitCounter;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	// 重设密码
	@RequestMapping(value = "/resetPassword.do")
	@ResponseBody
	public Object resetPassword(ResetPasswordVo restpasswordVo,HttpServletRequest request,HttpServletResponse response) {
		try {
			ParamValidateUtil.validateNull(restpasswordVo.getCode(), "请输入有效的验证码！");
			ParamValidateUtil.validatePhone(restpasswordVo.getPhone(), UserConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(restpasswordVo.getPhone(), 20, UserConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(restpasswordVo.getPassWord(), UserConstant.invalid_arguments);
			ParamValidateUtil.validatePositive(restpasswordVo.getPlatformCode(), ValidateConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return userService.resetPassword(restpasswordVo, request, response);

	}

	// 登录
	@RequestMapping(value = "/remLogin.do")
	@ResponseBody
	public Object remLogin(HttpServletRequest request, HttpServletResponse response,
			LoginVo reLoginVo) {
		try {
			ParamValidateUtil.validateNull(reLoginVo, UserConstant.invalid_arguments);
			ParamValidateUtil.validatePhone(reLoginVo.getPhone(), UserConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(reLoginVo.getPasswd(), UserConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return userService.remLogin(request, response, reLoginVo);
	}
 
	// 注册
	@RequestMapping(value = "/register.do")
	@ResponseBody
	public Object register(RegisterVo registerVo) {
		try {
			ParamValidateUtil.validateNull(registerVo, UserConstant.invalid_arguments);
			ParamValidateUtil.validatePhone(registerVo.getPhone(), UserConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(registerVo.getPasswd(), UserConstant.invalid_arguments);
//			ParamValidateUtil.validateEmpty(registerVo.getValidateCode(), UserConstant.invalid_arguments);
//			ParamValidateUtil.validatePositive(registerVo.getPlatformCode(), ValidateConstant.platformUnvalidate);
			ParamValidateUtil.validateEmpty(registerVo.getUserName(), UserConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(registerVo.getUserName(), 15, UserConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return userService.register(registerVo);
	}
	
	// 更改头像
	@RequestMapping(value = "/uploadPortrait.do")
	@ResponseBody
	public Object uploadPortrait(MultipartFile file,
			@RequestParam(value = "width", defaultValue = "300") Integer width,
			@RequestParam(value = "hight", defaultValue = "300") Integer hight,
			HttpServletRequest request) {
		try {
			ParamValidateUtil.validateExpression(CommonUtil.isImage(file), UserConstant.invalid_image);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}

		return userService.uploadPortrait(file, width, hight,request);
	}

	// 更改用户信息
	@RequestMapping(value = "/uploadUser.do")
	@ResponseBody
	public Object uploadUser(UserVo user,HttpServletRequest request, HttpServletResponse response) {
		try {
			ParamValidateUtil.validateMaxLength(user.getMail(), 50, UserConstant.invalid_arguments);
			ParamValidateUtil.validateEmail(user.getMail(), UserConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(user.getSex(), 5, UserConstant.invalid_arguments);
			ParamValidateUtil.validateMaxLength(user.getUsername(), 15, UserConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return userService.uploadUser(user,request, response);
	}

	// 修改密码
	@RequestMapping(value = "/updatePassword.do")
	@ResponseBody
	public Object updatePassword(Integer platformCode,String oldPassword, String password) {
		try {
			ParamValidateUtil.validatePositive(platformCode, UserConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(oldPassword, UserConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(password, UserConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return userService.updatePassword(platformCode, oldPassword, password);
	}

	
	@RequestMapping("/findUserInfoById.do")
	@ResponseBody
	public Object findUserInfoById(String userId) {
		try {
			ParamValidateUtil.validateEmpty(userId, UserConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(Constant.failed_code, e.getMessage());
		}
		return userService.findUserInfoById(userId);
	}

	@RequestMapping("/visitCount.do")
	@ResponseBody
	public Long visitCount(HttpServletRequest request, HttpServletResponse response) {
		return visitCounter.increaseAndgetCount(request, response);
	}

	// 上传图片到服务器
	@RequestMapping("/uploadToWeb.do")
	@ResponseBody
	public Object uploadToWeb(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "width", defaultValue = "300") Integer width,
			@RequestParam(value = "hight", defaultValue = "300") Integer hight) {

		try {
			ParamValidateUtil.validateExpression(CommonUtil.isImage(file), UserConstant.invalid_image);
		} catch (ParamNotValidException e) {
			logger.warn(e.getMessage());
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return userService.uploadToWeb(file, width, hight);
	}

	// 更新图片
	@RequestMapping("/updatePicture.do")
	@ResponseBody
	public Object updatePicture(String portrait,HttpServletRequest request) {
		try {
			ParamValidateUtil.validateEmpty(portrait, UserConstant.invalid_image);
			ParamValidateUtil.validateMaxLength(portrait, 100, UserConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return userService.updatePicture( portrait,request);
	}

	// 注销
	@RequestMapping("/logout.do")
	@ResponseBody
	public Object logout(HttpServletRequest requqest,HttpServletResponse response) {
		return userService.logout(requqest,response);
	}

	// 微网站获取用户信息
	@RequestMapping("/getUserInfo.do")
	@ResponseBody
	public Object getUserInfo() {
		return userService.getUserInfo();
	}

	@RequestMapping("/changeoPassword.do")
	@ResponseBody
	public Object changePasswordVo(ChangePasswordVo changePasswordVo) {
		try {
			ParamValidateUtil.validateNull(changePasswordVo.getCode(), UserConstant.invalid_arguments);
		//	ParamValidateUtil.validatePhone(changePassword.getPhone(), UserConstant.phoneError);
		//	ParamValidateUtil.validateMaxLength(changePassword.getPhone(), 20, UserConstant.phone_over);
			ParamValidateUtil.validateEmpty(changePasswordVo.getPassword(), UserConstant.invalid_arguments);
			ParamValidateUtil.validatePositive(changePasswordVo.getPlatformCode(), ValidateConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(Constant.failed_code, e.getMessage());
		}
		return userService.changePasswordVo(changePasswordVo);
	}
}
