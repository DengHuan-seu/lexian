package com.chinasofti.lexian.manager.commodity.po;

public class CommoditySpecPo {
	private int specid;
	private String commodity_no;
	private String spec_group;
	private String spec_name;
	private int states;
	
	public int getSpecid() {
		return specid;
	}
	public void setSpecid(int specid) {
		this.specid = specid;
	}
	public String getCommodity_no() {
		return commodity_no;
	}
	public void setCommodity_no(String commodity_no) {
		this.commodity_no = commodity_no;
	}
	public String getSpec_group() {
		return spec_group;
	}
	public void setSpec_group(String group_name) {
		this.spec_group = group_name;
	}
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
}
