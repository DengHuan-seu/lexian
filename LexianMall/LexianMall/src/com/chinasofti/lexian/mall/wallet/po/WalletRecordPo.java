package com.chinasofti.lexian.mall.wallet.po;

import java.util.Date;

public class WalletRecordPo {
	private int id;
	private int wallet_id;
	private String order_no;
	private double amount;
	// 1:订单支付   2:充值   3:转账
	private int type;
	// 1：成功， -1：失败
	private int resulttype;
	private String description;
	private Date createtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(int wallet_id) {
		this.wallet_id = wallet_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getResulttype() {
		return resulttype;
	}
	public void setResulttype(int resulttype) {
		this.resulttype = resulttype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
