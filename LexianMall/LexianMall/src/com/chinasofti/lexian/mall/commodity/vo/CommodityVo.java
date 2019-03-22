package com.chinasofti.lexian.mall.commodity.vo;

import java.sql.Timestamp;

import com.chinasofti.lexian.mall.commodity.po.CommodityPo;

public class CommodityVo {
	private int id;
	private String commodityNo;
	private String name;
	private String introduce;
	private String pictureurl;
	private Timestamp createtime;
	private Timestamp updatetime;
	private int states;
	
	public CommodityVo(){
	}
	
	public CommodityVo(CommodityPo cp){
		this.id = cp.getId();
		this.commodityNo = cp.getCommodity_no();
		this.name = cp.getName();
		this.introduce = cp.getIntroduce();
		this.pictureurl = cp.getPictureurl();
		this.createtime = cp.getCreatetime();
		this.updatetime = cp.getUpdatetime();
		this.states = cp.getStates();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	public String getFullPictureUrl(){
		return com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath + this.pictureurl;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
}
