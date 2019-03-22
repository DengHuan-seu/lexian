package com.chinasofti.lexian.manager.store.vo;

import com.chinasofti.lexian.manager.store.po.StoreCommodityPo;

public class StoreCommodityVo {
	private int id;
	private String commodityNo;
	private String commodityName;
	private String storeNo;
	private int stock;
	private double listPrice;
	private double realPrice;
	private Boolean active;
	
	public StoreCommodityVo(){
		
	}
	
	public StoreCommodityVo(StoreCommodityPo po){
		this.id = po.getId();
		this.commodityNo = po.getCommodity_no();
		this.commodityName = po.getName();
		this.storeNo = po.getStore_no();
		this.stock = po.getCommodity_amont();
		this.listPrice = po.getCommodity_price();
		this.realPrice = po.getReal_price();
		this.active = (po.getType() == 1 ? true : false);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public double getListPrice() {
		return listPrice;
	}
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	public double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
