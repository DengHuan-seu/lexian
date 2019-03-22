package com.chinasofti.lexian.mall.commodity.vo;

import java.util.ArrayList;
import java.util.List;

import com.chinasofti.lexian.mall.commodity.po.CommodityPicturePo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPricePo;

// 专用于Android App数据传输的VO
public class CommodityDetail {
	private String commodityNo;
    private String commodityName;
    private String pictureUrl;
    private String introduce;
    private String detailed;
    private String storeNo;
    private double listPrice;
    private double originPrice;
    private int stock;
    private List<String> pictures;
    
    public CommodityDetail(){
    	
    }
    
    public CommodityDetail(CommodityPo commodity, 
			List<CommodityPicturePo> pictures,
			CommodityPricePo price){
		this.commodityNo = commodity.getCommodity_no();
		this.commodityName = commodity.getName();
		this.introduce = commodity.getIntroduce();
		this.pictureUrl = commodity.getPictureurl();
		this.detailed = commodity.getDetailed();
		this.storeNo = price.getStore_no();
		this.listPrice = price.getCommodity_price();
		this.originPrice = price.getReal_price();
		this.stock = price.getCommodity_amont();
		
		this.pictures = new ArrayList<String>();
		for(CommodityPicturePo cpp : pictures){
			this.pictures.add(com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath +
					cpp.getPicture_url());
		}
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
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public double getListPrice() {
		return listPrice;
	}
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	public double getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(double originPrice) {
		this.originPrice = originPrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public List<String> getPictures() {
		return pictures;
	}
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	public String getFullPictureUrl(){
		if(pictureUrl != null){
			return com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath + this.pictureUrl;
		} else{
			return "";
		}
	}
	public String getFullDetailed(){
		if(this.getDetailed() != null){
			return this.getDetailed().replace("src=\"", 
				"src=\"" + com.chinasofti.lexian.mall.common.util.Config.PicServerVirtualPath);
		} else{
			return null;
		}
	}
}
