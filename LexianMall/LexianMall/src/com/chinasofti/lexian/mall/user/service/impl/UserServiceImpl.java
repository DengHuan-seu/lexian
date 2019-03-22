package com.chinasofti.lexian.mall.user.service.impl;

import java.sql.Timestamp;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chinasofti.lexian.mall.common.dao.BaseRedisDao;
import com.chinasofti.lexian.mall.common.service.BaseService;
import com.chinasofti.lexian.mall.common.util.AES;
import com.chinasofti.lexian.mall.common.util.CommonUtil;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.MessageSender;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.common.util.SHA;
import com.chinasofti.lexian.mall.user.constant.UserConstant;
import com.chinasofti.lexian.mall.user.dao.UserDao;
import com.chinasofti.lexian.mall.user.po.User;
import com.chinasofti.lexian.mall.user.service.UserService;
import com.chinasofti.lexian.mall.user.vo.ChangePasswordVo;
import com.chinasofti.lexian.mall.user.vo.LoginVo;
import com.chinasofti.lexian.mall.user.vo.ResetPasswordVo;
import com.chinasofti.lexian.mall.user.vo.RegisterVo;
import com.chinasofti.lexian.mall.user.vo.UserVo;
import com.chinasofti.lexian.mall.validation.constant.ValidateConstant;
import com.chinasofti.lexian.mall.validation.dao.ValidateCodeDao;
import com.chinasofti.lexian.mall.validation.po.ValidationCodePo;
import com.chinasofti.lexian.mall.wallet.dao.WalletDao;
import com.chinasofti.lexian.mall.wallet.po.WalletPo;

@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {
	private MessageSender messageSender;
	private UserDao userDao;
	private WalletDao walletDao;
	private BaseRedisDao baseRedisDao;
	private ValidateCodeDao validateCodeDao;

	@Autowired
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setBaseRedisDao(BaseRedisDao baseRedisDao) {
		this.baseRedisDao = baseRedisDao;
	}

	@Autowired
	public void setWalletDao(WalletDao walletDao) {
		this.walletDao = walletDao;
	}
	
	@Autowired
	public void setValidateCodeDao(ValidateCodeDao validateCodeDao) {
		this.validateCodeDao = validateCodeDao;
	}
	
	private String cryptoKey;
	private String portraitPhysicalPath;
	private String portraitVirtualPath;
	private String defaultPictureVirtualPath;

	@Value("${portraitVirtualPath}")
	public void setWebPath(String portraitVirtualPath) {
		this.portraitVirtualPath = portraitVirtualPath;
	}

	public String getWebPath() {
		return portraitVirtualPath;
	}

	@Value("${defaultPictureVirtualPath}")
	public void setDefaultpicturewebPath(String defaultPictureVirtualPath) {
		this.defaultPictureVirtualPath = defaultPictureVirtualPath;
	}

	public String getDefaultpicturewebPath() {
		return defaultPictureVirtualPath;
	}

	@Value("${portraitPhysicalPath}")
	public void setPortraitPhysicalPath(String portraitPhysicalPath) {
		this.portraitPhysicalPath = portraitPhysicalPath;
	}

	public String getPortraitPhysicalPath() {
		return portraitPhysicalPath;
	}

	@Value("${cryptoKey}")
	public void setCryptoKey(String cryptoKey) {
		this.cryptoKey = cryptoKey;
	}

	@Override
	public ResultHelper remLogin(HttpServletRequest request, HttpServletResponse response, LoginVo reLoginVo) {
		String lexianId = CommonUtil.getCookieValue(request.getCookies(), Constant.LEXIANUSERKEY);
		// 用户已经登录
		if (com.chinasofti.lexian.mall.common.util.StringUtils.isNotNullAndEmpty(lexianId) && baseRedisDao.existKey(lexianId)) {
			return new ResultHelper(2, UserConstant.user_loggedin, baseRedisDao.getObject(lexianId));
		}
		User queryUser = new User();
		queryUser.setPhone(reLoginVo.getPhone());
		User userinfo = userDao.getUser(queryUser);
		if (null == userinfo)
			return new ResultHelper(Constant.failed_code, UserConstant.user_notfound);
		if (userinfo.getStatus() == UserConstant.disabled)
			return new ResultHelper(Constant.failed_code, UserConstant.user_disabled);
		try {
			String aesPassword = reLoginVo.getPasswd();
			if (Integer.valueOf(Constant.app).equals(reLoginVo.getPlatformCode()))
				aesPassword = AES.decryptECB(aesPassword, cryptoKey);
			queryUser.setPasswd(SHA.instance.getEncryptResult(aesPassword));
		} catch (Exception e) {
			return new ResultHelper(Constant.failed_code, UserConstant.error);
		}
		
		// 查找匹配的用户
		userinfo = userDao.getUser(queryUser);
		if (null == userinfo)
			return new ResultHelper(Constant.failed_code, UserConstant.invalid_login);
		userinfo.setLastlogintime(new Timestamp(System.currentTimeMillis()));
		userDao.updateUser(userinfo);
		
		// 用户信息写入redis
		final int expires = com.chinasofti.lexian.mall.common.util.Config.CookieExpiredSeconds;
		lexianId = UUID.randomUUID().toString();
		userinfo.setSessionKey(lexianId);
		baseRedisDao.setExObject(lexianId, expires, userinfo);
		Cookie cookie = new Cookie(Constant.LEXIANUSERKEY, lexianId);
		cookie.setMaxAge(expires);
		cookie.setPath("/");
		response.addCookie(cookie);
		return new ResultHelper(Constant.success_code, UserConstant.success, userinfo);
	}

	@Override
	public ResultHelper register(RegisterVo registerVo) {
		User user = new User();
		user.setPhone(registerVo.getPhone());
		User userinfo = userDao.getUser(user);
		if (userinfo != null) {
			return new ResultHelper(Constant.failed_code, UserConstant.duplicate_user);
		}
		// 暂不处理验证码
//		ValidationCodePo validationCodePo = new ValidationCodePo();
//		validationCodePo.setPlatformCode(registerVo.getPlatformCode());
//		validationCodePo.setPhone(registerVo.getPhone());
//		validationCodePo.setCode(registerVo.getValidateCode());
//		validationCodePo.setType(ValidateConstant.type_1);
//		if (!validateCodeDao.isExistValidateCode(validationCodePo))
//			return new ResultHelper(Constant.failed_code, UserConstant.Code_error);
//		validateCodeDao.expireValidateCode(validationCodePo);

		user.setId(UUID.randomUUID().toString());
		String plainPassword = registerVo.getPasswd();
		String passwd = registerVo.getPasswd();
		if (Integer.valueOf(Constant.app).equals(registerVo.getPlatformCode())) {
			try {
				passwd = AES.decryptECB(registerVo.getPasswd(), cryptoKey);
			} catch (Exception exception) {
				return new ResultHelper(Constant.failed_code, UserConstant.error);
			}
		}
		
		// 添加用户
		user.setPasswd(SHA.instance.getEncryptResult(passwd));
		user.setUsername(registerVo.getUserName());
		user.setStatus(UserConstant.enabled);
		String portrait = "/defaultpicture/1.jpg";
		user.setPortrait(portrait);
		userDao.saveUser(user);
		
		// 创建钱包
		WalletPo wallet = new WalletPo();
		wallet.setUser_id(user.getId());
		wallet.setPassword(plainPassword);
		wallet.setBalance(0.0);
		walletDao.addWallet(wallet);
		
		return new ResultHelper(Constant.success_code, UserConstant.success);
	}

	@Override
	public ResultHelper uploadPortrait(MultipartFile multipartFile, Integer width, Integer hight,HttpServletRequest request) {
		String imageURL = saveImageRule(multipartFile, portraitVirtualPath, portraitPhysicalPath, width, hight);
		if (null == imageURL) {
			return new ResultHelper(Constant.failed_code, UserConstant.error_saveimage);
		}
		updatePicture(imageURL, request);
		return new ResultHelper(Constant.success_code, UserConstant.success, imageURL);
	}

	@Override
	public ResultHelper uploadUser(UserVo user, HttpServletRequest request, HttpServletResponse response) {

		User uploadUser = new User();
		User redis = getUser();
		BeanUtils.copyProperties(user, uploadUser);
		uploadUser.setId(redis.getId());
		userDao.updateUser(uploadUser);
		
		// 将用户信息放在redis内
		User user2 = new User();
		String lexianId = CommonUtil.getCookieValue(request.getCookies(), Constant.LEXIANUSERKEY);
		BeanUtils.copyProperties(user, user2);
		user2.setId(redis.getId());
		user2.setPortrait(redis.getPortrait());
		user2.setPhone(redis.getPhone());
//		user2.setUsername(redis.getUsername());
		baseRedisDao.setObject(lexianId, user2);
		
		return new ResultHelper(Constant.success_code, UserConstant.success, user);
	}

	@Override
	public ResultHelper resetPassword(ResetPasswordVo resetPasswordVo, HttpServletRequest request,HttpServletResponse response) {
		User user = new User();
		user.setPhone(resetPasswordVo.getPhone());
		User userInfo = userDao.getUser(user);
		if (userInfo == null) {
			return new ResultHelper(Constant.failed_code, UserConstant.user_notfound);
		}
		
		ValidationCodePo validationCodePo = new ValidationCodePo();
		validationCodePo.setPlatformCode(resetPasswordVo.getPlatformCode());
		validationCodePo.setPhone(resetPasswordVo.getPhone());
		validationCodePo.setCode(resetPasswordVo.getCode());
		validationCodePo.setType(ValidateConstant.type_forgetpassword);
		if (!validateCodeDao.isExistValidateCode(validationCodePo))
			return new ResultHelper(Constant.failed_code, ValidateConstant.wrong_code);
		validateCodeDao.expireValidateCode(validationCodePo);

		BeanUtils.copyProperties(userInfo, user);
		String passwd = resetPasswordVo.getPassWord();
		if (Integer.valueOf(Constant.app).equals(resetPasswordVo.getPlatformCode())) {
			try {
				passwd = AES.decryptECB(resetPasswordVo.getPassWord(), cryptoKey);
			} catch (Exception exception) {
				return new ResultHelper(Constant.failed_code, UserConstant.error);
			}
		}
		user.setPasswd(SHA.instance.getEncryptResult(passwd));
		userDao.updateUser(user);
		logout(request, response);
		return new ResultHelper(Constant.success_code, UserConstant.success);
	}

	@Override
	public ResultHelper updatePassword(Integer platformCode, String oldpassword, String password) {
		User user = new User();
		user.setId(getUser().getId());
		user.setPasswd(SHA.instance.getEncryptResult(oldpassword));
		User user1 = userDao.getUser(user);
		if (null == user1)
			return new ResultHelper(Constant.failed_code, UserConstant.user_notfound);
		if (Integer.valueOf(Constant.app).equals(platformCode)) {
			try {
				password = AES.decryptECB(password, cryptoKey);
			} catch (Exception e) {
				return new ResultHelper(Constant.failed_code, UserConstant.error);
			}
		}
		user.setPasswd(SHA.instance.getEncryptResult(password));
		userDao.updateUser(user);
		return new ResultHelper(Constant.success_code, UserConstant.success);
	}

	@Override
	public ResultHelper findUserInfoById(String userId) {
		User user = new User();
		user.setId(userId);
		User userInfo = userDao.getUser(user);
		userInfo.setPasswd(null);
		return new ResultHelper(Constant.success_code, UserConstant.success, userInfo);
	}

	@Override
	public ResultHelper uploadToWeb(MultipartFile file, Integer width, Integer hight) {
		String path = saveImageRule(file, portraitVirtualPath, portraitPhysicalPath, width, hight);
		if (null == path)
			return new ResultHelper(Constant.failed_code, UserConstant.error_saveimage);
		return new ResultHelper(Constant.success_code, UserConstant.success, path);
	}

	@Override
	public ResultHelper updatePicture(String portrait, HttpServletRequest request) {
		User user = new User();
		user.setId(getUser().getId());
		user.setPortrait(portrait.replace(com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath, ""));
		userDao.updateUser(user);

		String uyuesId = CommonUtil.getCookieValue(request.getCookies(), Constant.LEXIANUSERKEY);
		User user2 = getUser();
		user2.setPortrait(portrait);
		baseRedisDao.setObject(uyuesId, user2);
		return new ResultHelper(Constant.success_code, UserConstant.success);
	}

	@Override
	public ResultHelper getUserInfo() {
		User user = getUser();
		return new ResultHelper(Constant.success_code, UserConstant.success, user);
	}

	public ResultHelper changePasswordVo(ChangePasswordVo changePasswordVo) {
		ValidationCodePo validationCodePo = new ValidationCodePo();
		validationCodePo.setPlatformCode(changePasswordVo.getPlatformCode());
		validationCodePo.setPhone(getUser().getPhone());
		validationCodePo.setCode(changePasswordVo.getCode());
		validationCodePo.setType(ValidateConstant.type_changepassword);
		if (!validateCodeDao.isExistValidateCode(validationCodePo))
			return new ResultHelper(Constant.failed_code, ValidateConstant.wrong_code);
		validateCodeDao.expireValidateCode(validationCodePo);
		
		// 检查旧密码
		if(Integer.valueOf(Constant.pc).equals(changePasswordVo.getPlatformCode())){
			if(changePasswordVo.getOldPassword() == null){
				return new ResultHelper(Constant.failed_code, UserConstant.invalid_arguments);
			}
			User u = new User();
			u.setId(getUser().getId());
			u.setPasswd(SHA.instance.getEncryptResult(changePasswordVo.getOldPassword()));
			
			if(userDao.getUser(u)==null){
				return new ResultHelper(Constant.failed_code, UserConstant.invalid_originpassword);
			}
		}
		
		User user = new User();
		user.setId(getUser().getId());
		String passwd = changePasswordVo.getPassword();
		if (Integer.valueOf(Constant.app).equals(changePasswordVo.getPlatformCode())) {
			try {
				passwd = AES.decryptECB(changePasswordVo.getPassword(), cryptoKey);
			} catch (Exception exception) {
				return new ResultHelper(Constant.failed_code, UserConstant.error);
			}
		}
		user.setPasswd(SHA.instance.getEncryptResult(passwd));
		userDao.updateUser(user);
		
		// 发送短信
		messageSender.sendMessage(getUser().getPhone(), true, UserConstant.success_changepassword);
		
		// 修改钱包密码
		WalletPo wallet = walletDao.findWallet(user.getId());
		wallet.setPassword(passwd);
		walletDao.updateWalletPassword(wallet);
		
		return new ResultHelper(Constant.success_code, UserConstant.success);
	}

	@Override
	public ResultHelper logout(HttpServletRequest request, HttpServletResponse response) {
		if (getUser()!=null) {
			baseRedisDao.delete(getUser().getSessionKey());
		}
		response.addCookie(new Cookie(Constant.LEXIANUSERKEY, "none") {
			private static final long serialVersionUID = 1L;
			{
				setMaxAge(0);
				setPath("/");
			}
		});
		return new ResultHelper(Constant.success_code, UserConstant.success);

	}
}
