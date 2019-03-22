package com.chinasofti.lexian.mall.order.vo;

import com.chinasofti.lexian.mall.order.po.TopSalesCommodityPo;

public class TopSalesCommodityVo {
	private String commodityNo;
	private String commodityName;
	private String pictureurl;
	private int salesAmount;
	private double commodityPrice;
	
	public TopSalesCommodityVo(){
	}
	
	public TopSalesCommodityVo(TopSalesCommodityPo po){
		this.commodityNo = po.getCommodity_no();
		this.commodityName = po.getCommodity_name();
		this.pictureurl = po.getPictureurl();
		this.salesAmount = po.getCounts();
		this.commodityPrice = po.getCommodity_price();
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
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public int getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
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
}
