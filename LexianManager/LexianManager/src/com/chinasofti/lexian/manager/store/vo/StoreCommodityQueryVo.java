package com.chinasofti.lexian.manager.store.vo;

import com.chinasofti.lexian.manager.common.util.PageHelper;

public class StoreCommodityQueryVo extends PageHelper<StoreCommodityQueryVo> {
	private String commodityNo;
	private String commodityName;
	private String storeNo;
	private Boolean filterActive = false;
	private int active;
	
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Boolean getFilterActive() {
		return filterActive;
	}
	public void setFilterActive(Boolean filterActive) {
		this.filterActive = filterActive;
	}
}
