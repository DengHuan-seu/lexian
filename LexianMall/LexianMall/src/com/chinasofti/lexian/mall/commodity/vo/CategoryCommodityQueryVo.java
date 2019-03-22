package com.chinasofti.lexian.mall.commodity.vo;

import com.chinasofti.lexian.mall.common.util.PageHelper;

public class CategoryCommodityQueryVo extends PageHelper<CategoryCommodityQueryVo> {
	private int categoryId;
	private double minPrice;
	private double maxPrice;
	private Boolean orderByPrice;
	private Boolean orderBySales;
	private Boolean isDesc;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Boolean getOrderByPrice() {
		return orderByPrice;
	}
	public void setOrderByPrice(Boolean orderByPrice) {
		this.orderByPrice = orderByPrice;
	}
	public Boolean getOrderBySales() {
		return orderBySales;
	}
	public void setOrderBySales(Boolean orderBySales) {
		this.orderBySales = orderBySales;
	}
	public Boolean getIsDesc() {
		return isDesc;
	}
	public void setIsDesc(Boolean isDesc) {
		this.isDesc = isDesc;
	}
}
