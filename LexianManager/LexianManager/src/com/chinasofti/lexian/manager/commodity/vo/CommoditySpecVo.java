package com.chinasofti.lexian.manager.commodity.vo;

import com.chinasofti.lexian.manager.commodity.po.CommoditySpecPo;

public class CommoditySpecVo {
	private int specId;
	private String commodityNo;
	private String specGroup;
	private String specName;
	private int states;
	
	public CommoditySpecVo(){
		
	}
	
	public CommoditySpecVo(CommoditySpecPo po){
		this.specId = po.getSpecid();
		this.commodityNo = po.getCommodity_no();
		this.specGroup = po.getSpec_group();
		this.specName = po.getSpec_name();
		this.states = po.getStates();
	}
	
	public int getSpecId() {
		return specId;
	}
	public void setSpecId(int specId) {
		this.specId = specId;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getSpecGroup() {
		return specGroup;
	}
	public void setSpecGroup(String specGroup) {
		this.specGroup = specGroup;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
}
