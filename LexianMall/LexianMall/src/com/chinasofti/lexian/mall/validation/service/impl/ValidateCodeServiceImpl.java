package com.chinasofti.lexian.mall.validation.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.mall.common.service.BaseService;
import com.chinasofti.lexian.mall.common.util.CommonUtil;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.MessageSender;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.user.dao.UserDao;
import com.chinasofti.lexian.mall.user.po.User;
import com.chinasofti.lexian.mall.validation.constant.ValidateConstant;
import com.chinasofti.lexian.mall.validation.dao.ValidateCodeDao;
import com.chinasofti.lexian.mall.validation.po.ValidationCodePo;
import com.chinasofti.lexian.mall.validation.service.ValidateCodeService;
import com.chinasofti.lexian.mall.validation.vo.ValidationCodeVo;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@Service
@Transactional
public class ValidateCodeServiceImpl extends BaseService implements ValidateCodeService {
	@Value("${code_length}")
	private int code_length;

	@Value("${max_send}")
	private int max_send;

	private ValidateCodeDao validateCodeDao;

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private Cache validationCache;

	@Autowired
	public void setValidationCache(Cache validationCache) {
		this.validationCache = validationCache;
	}

	@Autowired
	public void setValidateCodeDao(ValidateCodeDao validateCodeDao) {
		this.validateCodeDao = validateCodeDao;
	}

	private MessageSender messageSender;

	@Autowired
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
	
	@Override
	public ResultHelper getValidateCode(ValidationCodeVo validationCodeVo) throws Exception {
		int type = validationCodeVo.getType();
		if(type < ValidateConstant.type_register || type > ValidateConstant.type_pay){
			return new ResultHelper(Constant.failed_code, ValidateConstant.invalid_type);
		}
		int platform = validationCodeVo.getPlatformCode();
		if(platform < ValidateConstant.platform_phone || platform > ValidateConstant.platform_all){
			return new ResultHelper(Constant.failed_code, ValidateConstant.invalid_platform);
		}
		
		User userinfo = null;
		if(type == ValidateConstant.type_register || type == ValidateConstant.type_forgetpassword){
			User user = new User();
			user.setPhone(validationCodeVo.getPhone());
			userinfo = userDao.getUser(user);
		} else{
			userinfo = getUser();
			validationCodeVo.setPhone(userinfo.getPhone());
		}
		
		// 如果该用户在系统中不存在，那么只能是“注册”
		if (userinfo == null) {
			if (type != ValidateConstant.type_register){
				return new ResultHelper(Constant.failed_code, ValidateConstant.invalid_user);
			}
		} else{
			if (type == ValidateConstant.type_register){
				return new ResultHelper(Constant.failed_code, ValidateConstant.duplicate_user);
			}
		}
		
		ResultHelper resultHelper = new ResultHelper();
		
		// 生成验证码
		validationCodeVo.setCode(RandomStringUtils.randomNumeric(code_length));
		// 根据手机号码 平台号 类型 生成统一的key
		String key = CommonUtil.getSingleCacheKey(validationCodeVo, new String[] { "phone", "type", "platformCode" });
		// 检查缓存中是否仍存在验证码
		if (validationCache.get(key) != null) {
			return new ResultHelper(Constant.failed_code, ValidateConstant.code_notexpired);
		}
		
		// 检查是否超出该号码允许接收的最大短信条数
		int count = validateCodeDao.getValidationPhoneCount(validationCodeVo);
		if (max_send <= count) {
			return new ResultHelper(Constant.failed_code, ValidateConstant.code_outoflimit);
		}
		
		// 发送短信
		resultHelper = messageSender.sendMessage(validationCodeVo.getPhone(), true,
				ValidateConstant.contents[validationCodeVo.getType() - 1], 
				validationCodeVo.getCode());
		
		// 将新验证码写入数据库及存入缓存中
		if (resultHelper != null && Constant.success_code == resultHelper.getCode()) {
			ValidationCodePo codePo = new ValidationCodePo();
			BeanUtils.copyProperties(validationCodeVo, codePo);
			validateCodeDao.saveValidateCode(codePo);
			
			Element element = new Element(key, validationCodeVo.getCode());
			validationCache.put(element);
		} else {
			resultHelper = new ResultHelper();
			resultHelper.setCode(Constant.failed_code);
			resultHelper.setMessage(ValidateConstant.failed);
		}
		return resultHelper;
	}

}
