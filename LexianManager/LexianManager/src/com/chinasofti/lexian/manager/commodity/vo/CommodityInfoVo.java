package com.chinasofti.lexian.manager.commodity.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.lexian.manager.commodity.po.CommodityCategoryPo;
import com.chinasofti.lexian.manager.commodity.po.CommodityPicturePo;
import com.chinasofti.lexian.manager.commodity.po.CommodityPo;
import com.chinasofti.lexian.manager.commodity.po.CommoditySpecPo;

public class CommodityInfoVo {
	// 基本信息
	private int id;
	private String commodityNo;
	private String name;
	private int categoryId;
	private String introduce;
	private String detailed;
	private String pictureurl;
	private Timestamp createtime;
	private Timestamp updatetime;
	private int states;
	
	// 图片信息
	private List<CommodityPictureVo> pictures;
	
	// 类别信息
	private String categoryName;
	private int parentCategoryId;
	private String parentCategoryName;
	private int topCategoryId;
	private String topCategoryName;
	
	// 规格信息
	private List<CommoditySpecVo> specs;
	
	public CommodityInfoVo(){
	}
	
	public CommodityInfoVo(CommodityPo cp, 
			List<CommodityPicturePo> cpps, 
			CommodityCategoryPo cat,
			List<CommoditySpecPo> specs){
		this.id = cp.getId();
		this.commodityNo = cp.getCommodity_no();
		this.name = cp.getName();
		this.introduce = cp.getIntroduce();
		this.pictureurl = cp.getPictureurl();
		this.createtime = cp.getCreatetime();
		this.updatetime = cp.getUpdatetime();
		this.states = cp.getStates();
		this.detailed = cp.getDetailed();
		this.categoryId = cp.getCategory_id();
		this.categoryName = cp.getCategoryname();
		
		this.pictures = new ArrayList<CommodityPictureVo>();
		for(CommodityPicturePo cpp : cpps){
			CommodityPictureVo cpv = new CommodityPictureVo(cpp);
			this.pictures.add(cpv);
		}
		
		this.parentCategoryId = cat.getSecondId();
		this.parentCategoryName = cat.getSecondName();
		this.topCategoryId = cat.getFirstId();
		this.topCategoryName = cat.getFirstName();
		
		if(specs != null && specs.size() > 0){
			this.specs = new ArrayList<CommoditySpecVo>();
			for(CommoditySpecPo po : specs){
				CommoditySpecVo vo = new CommoditySpecVo(po);
				this.specs.add(vo);
			}
		}
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
			return com.chinasofti.lexian.manager.common.util.Config.PicServerVirtualPath + this.pictureurl;
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	public int getTopCategoryId() {
		return topCategoryId;
	}

	public void setTopCategoryId(int topCategoryId) {
		this.topCategoryId = topCategoryId;
	}

	public String getTopCategoryName() {
		return topCategoryName;
	}

	public void setTopCategoryName(String topCategoryName) {
		this.topCategoryName = topCategoryName;
	}
	
	public String getFullDetailed(){
		if(this.detailed != null){
			return this.detailed.replace("src=\"", 
				"src=\"" + com.chinasofti.lexian.manager.common.util.Config.PicServerVirtualPath);
		} else{
			return null;
		}
	}

	public List<CommoditySpecVo> getSpecs() {
		return specs;
	}

	public void setSpecs(List<CommoditySpecVo> specs) {
		this.specs = specs;
	}
}
