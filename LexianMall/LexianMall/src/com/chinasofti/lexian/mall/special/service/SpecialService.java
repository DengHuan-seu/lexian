package com.chinasofti.lexian.mall.special.service;

import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.special.po.SpecialCommodityPo;

public interface SpecialService {
	public ResultHelper findSpecialCommodityBySpecialId(SpecialCommodityPo specialCommodityPo);
}
