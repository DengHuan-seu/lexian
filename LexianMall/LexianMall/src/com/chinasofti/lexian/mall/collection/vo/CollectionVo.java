package com.chinasofti.lexian.mall.collection.vo;

import java.sql.Timestamp;

import com.chinasofti.lexian.mall.collection.po.CollectionPo;

public class CollectionVo {
	private int id;
	private String userId;
	private Timestamp collectTime;
	private String storeNo;
	private String commodityNo;
	private String commodityName;
	private String storeName;
	private String pictureUrl;
	private double commodityPrice;
	
	public CollectionVo(){
	}
	
	public CollectionVo(CollectionPo po){
		this.id = po.getId();
		this.userId = po.getUser_id();
		this.collectTime = po.getCollecttime();
		this.storeNo = po.getStore_no();
		this.commodityNo = po.getCommodity_no();
		this.commodityName = po.getCommodity_name();
		this.storeName = po.getStorename();
		this.pictureUrl = po.getPictureurl();
		this.commodityPrice = po.getCommodity_price();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Timestamp collectTime) {
		this.collectTime = collectTime;
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
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getFullPictureUrl(){
		return com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath + this.pictureUrl;
	}

	public double getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(double commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
}
