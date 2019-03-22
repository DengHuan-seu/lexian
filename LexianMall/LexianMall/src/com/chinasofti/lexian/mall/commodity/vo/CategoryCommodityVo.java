package com.chinasofti.lexian.mall.commodity.vo;

import com.chinasofti.lexian.mall.commodity.po.CategoryCommodityPo;

public class CategoryCommodityVo {
	private String commodityNo;
	private String commodityName;
	private String introduce;
	private String pictureurl;
	private double commodityPrice;
	private int salesCount;
	
	public CategoryCommodityVo(){
		
	}
	
	public CategoryCommodityVo(CategoryCommodityPo po){
		this.commodityNo = po.getCommodity_no();
		this.commodityName = po.getCommodity_name();
		this.introduce = po.getIntroduce();
		this.pictureurl = po.getPictureurl();
		this.commodityPrice = po.getCommodity_price();
		this.salesCount = po.getCounts();
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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public double getCommodityPrice() {
		return commodityPrice;
	}
	public void setCommodityPrice(double commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
	public String getFullPictureurl(){
		return com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath + this.pictureurl;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCounts) {
		this.salesCount = salesCounts;
	}
}
