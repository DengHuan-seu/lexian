package com.chinasofti.lexian.mall.user.vo;

public class LoginVo {
	private String phone;
	private String passwd;
	private int platformCode;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
    
	public int getPlatformCode() {
		return platformCode;
	}
	
	public void setPlatformCode(int platformCode) {
		this.platformCode = platformCode;
	}
	
}
