package com.chinasofti.lexian.manager.commodity.vo;

import com.chinasofti.lexian.manager.common.util.PageHelper;

public class CommodityQueryVo extends PageHelper<CommodityQueryVo> {
	private String name;
	private String commodityNo;
	private int categoryId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
