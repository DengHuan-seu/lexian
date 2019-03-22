package com.chinasofti.lexian.mall.wallet.vo;

import com.chinasofti.lexian.mall.wallet.po.WalletPo;

public class WalletVo {
	private int id;
	private String userId;
	private double balance;
	
	public WalletVo(){
	}
	
	public WalletVo(WalletPo po){
		this.id = po.getId();
		this.userId = po.getUser_id();
		this.balance = po.getBalance();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
