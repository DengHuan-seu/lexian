package com.chinasofti.lexian.mall.collection.vo;

import com.chinasofti.lexian.mall.common.util.PageHelper;

public class CollectionQueryVo extends PageHelper<CollectionQueryVo> {
	private String userId;
	private String commodityNo;
	private String storeNo;
	private int Id;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
}
