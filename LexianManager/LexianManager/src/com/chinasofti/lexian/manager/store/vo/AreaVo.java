package com.chinasofti.lexian.manager.store.vo;

public class AreaVo {
	/**
	 * 父id
	 */
	private String parentId;
	/**
	 * 城市级别
	 */
	private Integer clazz;
	/**
	 * 城市名
	 */
	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getClazz() {
		return clazz;
	}

	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}

}
