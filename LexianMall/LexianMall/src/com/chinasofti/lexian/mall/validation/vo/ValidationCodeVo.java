package com.chinasofti.lexian.mall.validation.vo;

public class ValidationCodeVo {
	private String phone;
	private int type;
	private int platformCode;

	public String getPhone() {
		return phone;
	}

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public int getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(int platformCode) {
		this.platformCode = platformCode;
	}

}
