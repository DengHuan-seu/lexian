package com.chinasofti.lexian.manager.special.vo;

import com.chinasofti.lexian.manager.common.util.PageHelper;

public class SpecialCommodityVo extends PageHelper<SpecialCommodityVo>{
	private String specialCommodityId;
	private String commodityNo;
	private int specialId;
	public int getSpecialId() {
		return specialId;
	}
	public void setSpecialId(int specialId) {
		this.specialId = specialId;
	}
	public String getSpecialCommodityId() {
		return specialCommodityId;
	}
	public void setSpecialCommodityId(String specialCommodityId) {
		this.specialCommodityId = specialCommodityId;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	
}
