                                                                                     
package com.chinasofti.lexian.mall.validation.service;

import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.validation.vo.ValidationCodeVo;

public interface ValidateCodeService{
	public ResultHelper getValidateCode(ValidationCodeVo validationCodeVo) throws Exception;
}
