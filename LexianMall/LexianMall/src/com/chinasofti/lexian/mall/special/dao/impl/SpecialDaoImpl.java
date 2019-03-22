package com.chinasofti.lexian.mall.special.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.common.dao.BaseDao;
import com.chinasofti.lexian.mall.special.dao.SpecialDao;
import com.chinasofti.lexian.mall.special.po.SpecialCommodityPo;
@Repository
public class SpecialDaoImpl extends BaseDao implements SpecialDao{

	@Override
	public List<SpecialCommodityPo> findSpecialCommodity(SpecialCommodityPo specialCommodityPo) {
		return selectList("findSpecialCommodity", specialCommodityPo);
	}

}
