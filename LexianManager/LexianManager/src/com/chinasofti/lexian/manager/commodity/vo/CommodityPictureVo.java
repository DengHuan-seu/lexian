package com.chinasofti.lexian.manager.commodity.vo;

import org.springframework.web.multipart.MultipartFile;

import com.chinasofti.lexian.manager.commodity.po.CommodityPicturePo;

public class CommodityPictureVo {
	private int id;
	private String commodityNo;
	private String pictureUrl;
	
	// 下列属性用于上传商品主图片和配图
	// 请注意这两个属性的名字必须与页面上$.ajaxFileUpload函数调用中的fileElementId属性一致
	private MultipartFile fileMainPicture;
	private MultipartFile fileSubPicture;
	
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
		return com.chinasofti.lexian.manager.common.util.Config.PicServerVirtualPath + this.pictureUrl;
	}

	public MultipartFile getFileMainPicture() {
		return fileMainPicture;
	}

	public void setFileMainPicture(MultipartFile fileMainPicture) {
		this.fileMainPicture = fileMainPicture;
	}

	public MultipartFile getFileSubPicture() {
		return fileSubPicture;
	}

	public void setFileSubPicture(MultipartFile fileSubPicture) {
		this.fileSubPicture = fileSubPicture;
	}

}
