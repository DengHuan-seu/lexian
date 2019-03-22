package com.chinasofti.lexian.manager.special.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.manager.common.dao.BaseDao;
import com.chinasofti.lexian.manager.special.dao.SpecialDao;
import com.chinasofti.lexian.manager.special.po.SpecialCommodityPo;
import com.chinasofti.lexian.manager.special.po.SpecialPo;
import com.chinasofti.lexian.manager.special.vo.SpecialCommodityVo;

@Repository
public class SpecialDaoImpl extends BaseDao implements SpecialDao {

	@Override
	public void addSpecial(String specialName) {
		insert("addSpecial", specialName);
	}

	@Override
	public void deleteSpecial(String specialId) {
		delete("deleteSpecial", specialId);
	}

	@Override
	public void updateSpecial(SpecialPo specialPo) {
		update("updateSpecial", specialPo);
	}

	@Override
	public List<SpecialPo> selectSpecial(SpecialPo specialPo) {
		return selectList("selectSpecial", specialPo);
	}

	@Override
	public List<SpecialCommodityPo> findSpecialCommodity(SpecialCommodityVo specialCommodityVo) {
		return selectList("findSpecialCommodity", specialCommodityVo);
	}

	@Override
	public void addSpecialCommodity(SpecialCommodityVo specialCommodityVo) {
		insert("addSpecialCommodity", specialCommodityVo);
	}

	@Override
	public void deleteSpecialCommodity(SpecialCommodityVo specialCommodityVo) {
		delete("deleteSpecialCommodity", specialCommodityVo);
	}

}
