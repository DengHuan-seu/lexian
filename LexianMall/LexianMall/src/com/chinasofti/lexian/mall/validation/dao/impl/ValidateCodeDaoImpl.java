package com.chinasofti.lexian.mall.validation.dao.impl;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.common.dao.BaseDao;
import com.chinasofti.lexian.mall.validation.dao.ValidateCodeDao;
import com.chinasofti.lexian.mall.validation.po.ValidationCodePo;
import com.chinasofti.lexian.mall.validation.vo.ValidationCodeVo;

@Repository
public class ValidateCodeDaoImpl extends BaseDao implements ValidateCodeDao {
	@Override
	public int getValidationPhoneCount(ValidationCodeVo validationCodeVo) {
		return selectOne("getValidationPhoneCount", validationCodeVo);
	}

	@Override
	public void saveValidateCode(ValidationCodePo validationCodeForm) {

		insert("saveValidateCode", validationCodeForm);
	}

	@Override
	public boolean isExistValidateCode(ValidationCodePo validationCodeForm) {
		Integer count = selectOne("isExistValidateCode", validationCodeForm);
		return null!=count&&count > 0;
	}

	@Override
	public int expireValidateCode(ValidationCodePo validationCodeForm) {
         return update("expireValidateCode", validationCodeForm);
	}
}
