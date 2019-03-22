package com.chinasofti.lexian.manager.special.service;

import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.special.po.SpecialPo;
import com.chinasofti.lexian.manager.special.vo.SpecialCommodityVo;

public interface SpecialService {

	public ResultHelper addSpecial(String specialName);
	public ResultHelper deleteSpecial(String specialId);
	public ResultHelper updateSpecial(SpecialPo specialPo);
	public ResultHelper selectSpecial(SpecialPo specialPo);
	public ResultHelper addSpecialCommodity(SpecialCommodityVo specialCommodityVo);
	public ResultHelper deleteSpecialCommodity(SpecialCommodityVo specialCommodityVo);
	public ResultHelper findSpecialCommodities(SpecialCommodityVo specialCommodityVo);
}
