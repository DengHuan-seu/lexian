package com.chinasofti.lexian.mall.user.vo;

public class ResetPasswordVo {
	private String phone;
	private int platformCode;
	private String code;
	private String passWord;

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhone() {
		return phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(int platformCode) {
		this.platformCode = platformCode;
	}
}
