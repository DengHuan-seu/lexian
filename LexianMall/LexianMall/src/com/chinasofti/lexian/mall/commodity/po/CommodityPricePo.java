package com.chinasofti.lexian.mall.commodity.po;

public class CommodityPricePo {
	private String commodity_no;
	private double min_price;
	private double max_price;
	private String store_no;
	private double commodity_price;
	private double real_price;
	private int commodity_amont;
	
	public String getCommodity_no() {
		return commodity_no;
	}
	public void setCommodity_no(String commodity_no) {
		this.commodity_no = commodity_no;
	}
	public double getMin_price() {
		return min_price;
	}
	public void setMin_price(double min_price) {
		this.min_price = min_price;
	}
	public double getMax_price() {
		return max_price;
	}
	public void setMax_price(double max_price) {
		this.max_price = max_price;
	}
	public String getStore_no() {
		return store_no;
	}
	public void setStore_no(String store_no) {
		this.store_no = store_no;
	}
	public double getCommodity_price() {
		return commodity_price;
	}
	public void setCommodity_price(double commodity_price) {
		this.commodity_price = commodity_price;
	}
	public double getReal_price() {
		return real_price;
	}
	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}
	public int getCommodity_amont() {
		return commodity_amont;
	}
	public void setCommodity_amont(int commodity_amont) {
		this.commodity_amont = commodity_amont;
	}
}
