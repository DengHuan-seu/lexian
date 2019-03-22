package com.chinasofti.lexian.mall.special.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.mall.common.service.BaseService;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.special.constant.SpecialConstant;
import com.chinasofti.lexian.mall.special.dao.SpecialDao;
import com.chinasofti.lexian.mall.special.po.SpecialCommodityPo;
import com.chinasofti.lexian.mall.special.service.SpecialService;
@Service
@Transactional
public class SpecialServiceImpl extends BaseService implements SpecialService{
	private SpecialDao specialDao;
	
	@Autowired
	public void setSpecialDao(SpecialDao specialDao) {
		this.specialDao = specialDao;
	}
	
	@Override
	public ResultHelper findSpecialCommodityBySpecialId(SpecialCommodityPo specialCommodityPo) {
		List<SpecialCommodityPo> specialCommodityPos = specialDao.findSpecialCommodity(specialCommodityPo);
		return new ResultHelper(Constant.success_code,SpecialConstant.success,specialCommodityPos);
	}
}
