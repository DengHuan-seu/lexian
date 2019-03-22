package com.chinasofti.lexian.mall.special.dao;

import java.util.List;

import com.chinasofti.lexian.mall.special.po.SpecialCommodityPo;

public interface SpecialDao {
	public List<SpecialCommodityPo> findSpecialCommodity(SpecialCommodityPo specialCommodityPo);
}
