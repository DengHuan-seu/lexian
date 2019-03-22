package com.chinasofti.lexian.mall.store.po;

public class AreaPo {
	
	private String id;
	/**
	 * 城市名称
	 */
	private String city;
	/**
	 * 邮编
	 */
	private String postcode;

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
