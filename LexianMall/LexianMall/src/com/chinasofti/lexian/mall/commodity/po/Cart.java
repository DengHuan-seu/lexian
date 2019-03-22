package com.chinasofti.lexian.mall.commodity.po;

import java.sql.Timestamp;

public class Cart {
	private int trolleyId;
	private String userId;
	private int amont;
	private Timestamp createtime;
	private Timestamp updatetime;
	
	// 购物车状态 0 已删除 1 未删除 (撤销删除)
	private Integer states;
	private String commodityPicture;
	private double commodityPrice;
	
	// 商品名称
	private String commodityName;
	private String commodityIntroduce;
	private String storeNo;
	private String storeName;
	private String commodityNo;
	private double totalPrice;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAmont() {
		return amont;
	}

	public void setAmont(int amont) {
		this.amont = amont;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
	}

	public String getCommodityPicture() {
		return commodityPicture;
	}

	public void setCommodityPicture(String commodityPicture) {
		this.commodityPicture = commodityPicture;
	}

	public double getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(double commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public String getCommodityIntroduce() {
		return commodityIntroduce;
	}

	public void setCommodityIntroduce(String commodityIntroduce) {
		this.commodityIntroduce = commodityIntroduce;
	}

	public String getFullCommodityPicture(){
		return com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath + this.commodityPicture;
	}

	public int getTrolleyId() {
		return trolleyId;
	}

	public void setTrolleyId(int trolleyId) {
		this.trolleyId = trolleyId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
}
