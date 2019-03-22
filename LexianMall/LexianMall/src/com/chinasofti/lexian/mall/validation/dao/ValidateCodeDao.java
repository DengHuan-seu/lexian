package com.chinasofti.lexian.mall.validation.dao;

import com.chinasofti.lexian.mall.validation.po.ValidationCodePo;
import com.chinasofti.lexian.mall.validation.vo.ValidationCodeVo;


public interface ValidateCodeDao  {
    public int getValidationPhoneCount(ValidationCodeVo validationCodeVo);
	
    public void saveValidateCode(ValidationCodePo validationCodeForm ) ;
	
    public boolean isExistValidateCode(ValidationCodePo validationCodeForm);
    
    public int expireValidateCode(ValidationCodePo validationCodeForm);
}
