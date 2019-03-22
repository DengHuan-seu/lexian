package com.chinasofti.lexian.mall.commodity.po;

public class CategoryCommodityPo {
	private String commodity_no;
	private String commodity_name;
	private String introduce;
	private String pictureurl;
	private double commodity_price;
	private int counts;
	
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
	public double getCommodity_price() {
		return commodity_price;
	}
	public void setCommodity_price(double commodity_price) {
		this.commodity_price = commodity_price;
	}
	public int getCounts(){
		return this.counts;
	}
	public void setCounts(int counts){
		this.counts = counts;
	}
}
