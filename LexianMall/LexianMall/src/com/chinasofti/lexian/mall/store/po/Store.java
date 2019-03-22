package com.chinasofti.lexian.mall.store.po;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Store {
	/**
	 * 门店id
	 */
	private Long id;

	/**
	 * 城市id（所在区域）
	 */

	private Long citiesId;
	/**
	 * 门店名称
	 */
	private String storeName;

	/**
	 * 门店地址
	 */

	private String storeAddress;
	/**
	 * 开户行
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String bankCardNo;

	/**
	 * 户名
	 */

	private String userName;
	/**
	 * 公交信息
	 */
	private String transPortation;
	/**
	 * 门店介绍
	 */
	private String introduce;
	/**
	 * 联系方式（电话对外）
	 */

	private String telephone;

	/**
	 * 手机号（用于接收订单短信通知）
	 */
	private String phone;

	/**
	 * 营业时间（是否24小时1/0）
	 */

	private Integer shopHours;
	/**
	 * 开始营业时间
	 */
	private Date startTime;
	/**
	 * 停止营业时间
	 */
	private Date closeTime;
	/**
	 * 1、营业 2、停业
	 */
	private Integer status;
	/**
	 * 最大经度
	 */
	private Double maxLongitude;
	/**
	 * 最大维度
	 * 
	 */
	private Double maxLatitude;
	/**
	 * 最小经度
	 */
	private Double minLongitude;
	/**
	 * 最小维度
	 */
	private Double minLatitude;
	/**
	 * 纬度
	 */
	private Double latitude;
	/**
	 * 经度
	 */
	private Double longitude;
	
	private String storeNo;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getMaxLongitude() {
		return maxLongitude;
	}

	public void setMaxLongitude(Double maxLongitude) {
		this.maxLongitude = maxLongitude;
	}

	public Double getMaxLatitude() {
		return maxLatitude;
	}

	public void setMaxLatitude(Double maxLatitude) {
		this.maxLatitude = maxLatitude;
	}

	public Double getMinLongitude() {
		return minLongitude;
	}

	public void setMinLongitude(Double minLongitude) {
		this.minLongitude = minLongitude;
	}

	public Double getMinLatitude() {
		return minLatitude;
	}

	public void setMinLatitude(Double minLatitude) {
		this.minLatitude = minLatitude;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCitiesId() {
		return citiesId;
	}

	public void setCitiesId(Long citiesId) {
		this.citiesId = citiesId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTransPortation() {
		return transPortation;
	}

	public void setTransPortation(String transPortation) {
		this.transPortation = transPortation;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getShopHours() {
		return shopHours;
	}

	public void setShopHours(Integer shopHours) {
		this.shopHours = shopHours;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	@DateTimeFormat(iso = ISO.TIME, pattern = "HH:mm")
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	@DateTimeFormat(iso = ISO.TIME, pattern = "HH:mm")
	public void setClosrTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

}
