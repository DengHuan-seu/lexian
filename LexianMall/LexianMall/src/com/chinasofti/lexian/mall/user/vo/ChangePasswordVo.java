package com.chinasofti.lexian.mall.user.vo;

public class ChangePasswordVo {
	private String userId;
	private String phone;
	private String oldPassword;
	private String password;
	// 密码修改验证码的平台号
	private int platformCode;
	// 修改密码验证码
	private String code;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(int platformCode) {
		this.platformCode = platformCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
