package com.chinasofti.lexian.mall.commodity.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.lexian.mall.commodity.po.CommodityPicturePo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPricePo;
import com.chinasofti.lexian.mall.commodity.vo.CommodityPictureVo;

public class CommodityInfoVo {
	// 基本信息
	private int id;
	private String commodityNo;
	private String name;
	private String introduce;
	private String detailed;
	private String pictureurl;
	private Timestamp createtime;
	private Timestamp updatetime;
	private int states;
	
	// 图片信息
	private List<CommodityPictureVo> pictures;
	
	// 价格范围信息
	private double minPrice;
	private double maxPrice;
	
	public CommodityInfoVo(){
	}
	
	public CommodityInfoVo(CommodityPo commodity, 
			List<CommodityPicturePo> pictures,
			CommodityPricePo price){
		this.id = commodity.getId();
		this.commodityNo = commodity.getCommodity_no();
		this.name = commodity.getName();
		this.introduce = commodity.getIntroduce();
		this.pictureurl = commodity.getPictureurl();
		this.createtime = commodity.getCreatetime();
		this.updatetime = commodity.getUpdatetime();
		this.states = commodity.getStates();
		this.detailed = commodity.getDetailed();
		
		this.pictures = new ArrayList<CommodityPictureVo>();
		for(CommodityPicturePo cpp : pictures){
			CommodityPictureVo cpv = new CommodityPictureVo(cpp);
			this.pictures.add(cpv);
		}
		
		this.minPrice = price.getMin_price();
		this.maxPrice = price.getMax_price();
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
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
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
		if(pictureurl != null){
			return com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath + this.pictureurl;
		} else{
			return "";
		}
	}
	public List<CommodityPictureVo> getPictures() {
		return pictures;
	}
	public void setPictures(List<CommodityPictureVo> pictures){
		this.pictures = pictures;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	
	public String getFullDetailed(){
		if(this.detailed != null){
			return this.detailed.replace("src=\"", 
				"src=\"" + com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath);
		} else{
			return null;
		}
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
}
