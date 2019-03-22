package com.chinasofti.lexian.manager.commodity.dao;

import java.util.List;

import com.chinasofti.lexian.manager.commodity.po.CommodityPicturePo;
import com.chinasofti.lexian.manager.commodity.po.CommodityPo;
import com.chinasofti.lexian.manager.commodity.po.CommoditySpecPo;
import com.chinasofti.lexian.manager.commodity.po.CommodityCategoryPo;
import com.chinasofti.lexian.manager.commodity.vo.CommodityInfoVo;
import com.chinasofti.lexian.manager.commodity.vo.CommodityQueryVo;

public interface CommodityDao {
	public List<CommodityPo> getCommodityList(CommodityQueryVo commodityQueryVo);

	public Boolean hasExistedCommodityNo(String commodityNo);

	public void addCommodityInfo(CommodityPo po);
	
	public CommodityPo getCommodity(String commodityNo);

	public List<CommodityPicturePo> getCommodityPictures(String commodityNo);

	public CommodityCategoryPo getCommodityCategories(int categoryId);

	public void updateCommodityInfo(CommodityInfoVo civ);

	public void updateMainPicture(CommodityPicturePo cpp);

	public void addSubPicture(CommodityPicturePo cpp);

	public void deleteSubPicture(int id);

	public List<CommoditySpecPo> getCommoditySpecs(String commodityNo);

	public void deleteSpec(int specId);

	public void addSpec(CommoditySpecPo specPo);
}
