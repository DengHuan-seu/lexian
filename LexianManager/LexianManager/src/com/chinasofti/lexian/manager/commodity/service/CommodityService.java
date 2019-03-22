package com.chinasofti.lexian.manager.commodity.service;

import com.chinasofti.lexian.manager.commodity.vo.CommodityInfoVo;
import com.chinasofti.lexian.manager.commodity.vo.CommodityPictureVo;
import com.chinasofti.lexian.manager.commodity.vo.CommodityQueryVo;
import com.chinasofti.lexian.manager.commodity.vo.CommoditySpecVo;
import com.chinasofti.lexian.manager.commodity.vo.Fckeditor;
import com.chinasofti.lexian.manager.common.util.ResultHelper;

public interface CommodityService {
	public ResultHelper getCommodityList(CommodityQueryVo commodityQueryVo);

	public ResultHelper getCommodityInfo(String commodityNo);

	public String uploadFckPicture(Fckeditor fckeditor);

	public Object updateCommodityInfo(CommodityInfoVo civ);

	public Object updateMainPicture(CommodityPictureVo cpv);

	public Object updateSubPicture(CommodityPictureVo cpv);

	public Object deleteSubPicture(CommodityPictureVo cpv);

	public Object deleteSpec(int specId);

	public Object addSpec(CommoditySpecVo specVo);

	public Object addCommodityInfo(CommodityInfoVo vo);
}
