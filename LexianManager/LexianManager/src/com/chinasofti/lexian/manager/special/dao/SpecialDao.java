package com.chinasofti.lexian.manager.special.dao;

import java.util.List;

import com.chinasofti.lexian.manager.special.po.SpecialCommodityPo;
import com.chinasofti.lexian.manager.special.po.SpecialPo;
import com.chinasofti.lexian.manager.special.vo.SpecialCommodityVo;

public interface SpecialDao {

	public void addSpecial(String specialName);
	public void deleteSpecial(String specialId);
	public void updateSpecial(SpecialPo specialPo);
	public List<SpecialPo> selectSpecial(SpecialPo specialPo);
	public List<SpecialCommodityPo> findSpecialCommodity(SpecialCommodityVo specialCommodityVo);
	public void addSpecialCommodity(SpecialCommodityVo specialCommodityVo);
	public void deleteSpecialCommodity(SpecialCommodityVo specialCommodityVo);
}
