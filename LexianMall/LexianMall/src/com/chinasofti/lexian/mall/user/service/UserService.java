package com.chinasofti.lexian.mall.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.user.vo.ChangePasswordVo;
import com.chinasofti.lexian.mall.user.vo.LoginVo;
import com.chinasofti.lexian.mall.user.vo.ResetPasswordVo;
import com.chinasofti.lexian.mall.user.vo.UserVo;
import com.chinasofti.lexian.mall.user.vo.RegisterVo;

public interface UserService {
	public ResultHelper remLogin(HttpServletRequest request, HttpServletResponse registerVo, LoginVo reLoginVo);

	public ResultHelper register(RegisterVo reLoginVo);
	
	public ResultHelper uploadPortrait(MultipartFile multipartFile, Integer width, Integer hight,HttpServletRequest request);

	public ResultHelper uploadUser(UserVo user,HttpServletRequest request, HttpServletResponse registerVo);

	public ResultHelper resetPassword(ResetPasswordVo restpasswordVo,HttpServletRequest request,HttpServletResponse response);

	public ResultHelper updatePassword(Integer platformCode, String oldPassword, String password);

	public ResultHelper findUserInfoById(String userId);

	public ResultHelper uploadToWeb(MultipartFile file, Integer width, Integer hight);

	public ResultHelper updatePicture(String portrait,HttpServletRequest request);

	public ResultHelper logout(HttpServletRequest request,HttpServletResponse response);

	public ResultHelper getUserInfo();

	public ResultHelper changePasswordVo(ChangePasswordVo changePasswordVo);
}
