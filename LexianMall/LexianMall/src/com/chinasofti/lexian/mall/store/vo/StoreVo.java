package com.chinasofti.lexian.mall.store.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.chinasofti.lexian.mall.common.util.PageHelper;

public class StoreVo extends PageHelper<StoreVo> {
	private int id;
	private int citiesId;
	private String storeName;
	private String storeAddress;
	private int shopHours;
	private Date startTime;
	private Date closeTime;
	private int status;
	private double latitude;
	private double longitude;
	private double maxLongitude;
	private double maxLatitude;
	private double minLongitude;
	private double minLatitude;
	private String city;
	private String storeNo;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCitiesId() {
		return citiesId;
	}

	public void setCitiesId(int citiesId) {
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

	public int getShopHours() {
		return shopHours;
	}

	public void setShopHours(int shopHours) {
		this.shopHours = shopHours;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getMaxLongitude() {
		return maxLongitude;
	}

	public void setMaxLongitude(double maxLongitude) {
		this.maxLongitude = maxLongitude;
	}

	public double getMaxLatitude() {
		return maxLatitude;
	}

	public void setMaxLatitude(double maxLatitude) {
		this.maxLatitude = maxLatitude;
	}

	public double getMinLongitude() {
		return minLongitude;
	}

	public void setMinLongitude(double minLongitude) {
		this.minLongitude = minLongitude;
	}

	public double getMinLatitude() {
		return minLatitude;
	}

	public void setMinLatitude(double minLatitude) {
		this.minLatitude = minLatitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

}
