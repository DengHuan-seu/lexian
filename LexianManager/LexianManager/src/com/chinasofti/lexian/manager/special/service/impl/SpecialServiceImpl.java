package com.chinasofti.lexian.manager.special.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.special.constant.SpecialConstant;
import com.chinasofti.lexian.manager.special.dao.SpecialDao;
import com.chinasofti.lexian.manager.special.po.SpecialPo;
import com.chinasofti.lexian.manager.special.service.SpecialService;
import com.chinasofti.lexian.manager.special.vo.SpecialCommodityVo;
import com.chinasofti.lexian.manager.special.constant.SpecialConstant;
import com.chinasofti.lexian.manager.special.po.SpecialCommodityPo;

@Service
@Transactional
public class SpecialServiceImpl extends BaseService implements SpecialService {
	private SpecialDao specialDao;

	@Autowired
	public void setSpecialDao(SpecialDao specialDao) {
		this.specialDao = specialDao;
	}

	@Override
	public ResultHelper addSpecial(String specialName) {
		specialDao.addSpecial(specialName);
		return new ResultHelper(Constant.success_code, SpecialConstant.success);
	}

	@Override
	public ResultHelper deleteSpecial(String specialId) {
		specialDao.deleteSpecial(specialId);
		return new ResultHelper(Constant.success_code, SpecialConstant.success);
	}

	@Override
	public ResultHelper updateSpecial(SpecialPo specialPo) {
		specialDao.updateSpecial(specialPo);
		return new ResultHelper(Constant.success_code, SpecialConstant.success);
	}

	@Override
	public ResultHelper selectSpecial(SpecialPo specialPo) {
		specialPo.setIsgetTotal(true);
		List<SpecialPo> specialPos = specialDao.selectSpecial(specialPo);
		if(specialPos.size() == 0){
			return new ResultHelper(Constant.failed_code, SpecialConstant.failed);
		}
		return new ResultHelper(Constant.success_code, SpecialConstant.success,specialPos,specialPo.getTotal());
	}

	@Override
	public ResultHelper addSpecialCommodity(SpecialCommodityVo specialCommodityVo) {
		List<SpecialCommodityPo> specialCommodityPos = specialDao.findSpecialCommodity(specialCommodityVo);
		if(specialCommodityPos.size() != 0){
			return new ResultHelper(Constant.failed_code, SpecialConstant.failed);
		}
		specialDao.addSpecialCommodity(specialCommodityVo);
		return new ResultHelper(Constant.success_code, SpecialConstant.success);
	}

	@Override
	public ResultHelper deleteSpecialCommodity(SpecialCommodityVo specialCommodityVo) {
		specialDao.deleteSpecialCommodity(specialCommodityVo);
		return new ResultHelper(Constant.success_code, SpecialConstant.success);
	}

	@Override
	public ResultHelper findSpecialCommodities(SpecialCommodityVo specialCommodityVo) {
		List<SpecialCommodityPo> specialCommodityPos = specialDao.findSpecialCommodity(specialCommodityVo);
		return new ResultHelper(Constant.success_code, SpecialConstant.success,
				specialCommodityPos, specialCommodityVo.getTotal());
	}

}
