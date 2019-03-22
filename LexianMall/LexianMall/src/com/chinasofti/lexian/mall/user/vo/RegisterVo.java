package com.chinasofti.lexian.mall.user.vo;

public class RegisterVo {
	private String phone;
	private String passwd;
	private String validateCode;
	private String userName;
	private int type;
	private int platformCode;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public int getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(int platformCode) {
		this.platformCode = platformCode;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getType() {
		return type;
	}
}
