package com.chinasofti.lexian.manager.special.po;

public class SpecialCommodityPo {

	private String specialCommodityId;
	private String commodity_no;
	private String commodity_name;
	private String pictureurl;
	private int specialId;
	
	public String getSpecialCommodityId() {
		return specialCommodityId;
	}
	public void setSpecialCommodityId(String specialCommodityId) {
		this.specialCommodityId = specialCommodityId;
	}
	public int getSpecialId() {
		return specialId;
	}
	public void setSpecialId(int specialId) {
		this.specialId = specialId;
	}
	public String getCommodity_no() {
		return commodity_no;
	}
	public void setCommodity_no(String commodity_no) {
		this.commodity_no = commodity_no;
	}
	public String getCommodity_name() {
		return commodity_name;
	}
	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getFullPictureurl(){
		return com.chinasofti.lexian.manager.common.util.Config.PicServerVirtualPath + this.pictureurl;
	}
	
}
