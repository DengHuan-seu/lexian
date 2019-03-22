package com.chinasofti.lexian.mall.wallet.vo;

public class WalletTransferVo {
	private String targetUserPhone;
	private String targetUserName;
	private String password;
	private double amount;
	private String validationCode;
	
	public String getTargetUserPhone() {
		return targetUserPhone;
	}
	public void setTargetUserPhone(String targetUserPhone) {
		this.targetUserPhone = targetUserPhone;
	}
	public String getTargetUserName() {
		return targetUserName;
	}
	public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getValidationCode() {
		return validationCode;
	}
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
}
