package com.chinasofti.lexian.mall.commodity.vo;

import com.chinasofti.lexian.mall.commodity.po.CommodityPicturePo;

public class CommodityPictureVo {
	private int id;
	private String commodityNo;
	private String pictureUrl;
	
	public CommodityPictureVo(){
	}
	
	public CommodityPictureVo(CommodityPicturePo cpp){
		this.id = cpp.getId();
		this.commodityNo = cpp.getCommodity_no();
		this.pictureUrl = cpp.getPicture_url();
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
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getFullPictureUrl(){
		return com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath + this.pictureUrl;
	}
}
