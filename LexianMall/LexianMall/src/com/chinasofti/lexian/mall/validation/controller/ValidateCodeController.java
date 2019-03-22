package com.chinasofti.lexian.mall.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.mall.common.controller.BaseController;
import com.chinasofti.lexian.mall.common.util.ParamValidateUtil;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.common.util.ParamValidateUtil.ParamNotValidException;
import com.chinasofti.lexian.mall.validation.constant.ValidateConstant;
import com.chinasofti.lexian.mall.validation.service.ValidateCodeService;
import com.chinasofti.lexian.mall.validation.vo.ValidationCodeVo;

@Controller
@RequestMapping("/validation")
public class ValidateCodeController extends BaseController {

	private ValidateCodeService validateCodeService;

	@Autowired
	public void setValidateCodeService(ValidateCodeService validateCodeService) {
		this.validateCodeService = validateCodeService;
	}

	// 获取验证码
	@RequestMapping(value = "/getValidateCode.do")
	@ResponseBody
	public ResultHelper getValidateCode(ValidationCodeVo validationCodeVo) throws Exception {
		try {
			ParamValidateUtil.validateNull(validationCodeVo, ValidateConstant.invalid_arguments);
			ParamValidateUtil.validatePositive(validationCodeVo.getType(), ValidateConstant.invalid_arguments);
			ParamValidateUtil.validatePositive(validationCodeVo.getPlatformCode(), ValidateConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return validateCodeService.getValidateCode(validationCodeVo);
	}
}