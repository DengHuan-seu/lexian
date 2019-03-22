package com.chinasofti.lexian.mall.wallet.vo;

import java.util.Date;

import com.chinasofti.lexian.mall.wallet.po.WalletRecordPo;

public class WalletRecordVo {
	private int id;
	private int walletId;
	private String orderNo;
	private double amount;
	// 1:订单支付   2:充值   3:转账
	private int type;
	// 1：成功， -1：失败
	private int resultType;
	private String description;
	private Date createTime;
	private String userId;
	
	public WalletRecordVo(){
	}
	
	public WalletRecordVo(WalletRecordPo po){
		this.setId(po.getId());
		this.setWalletId(po.getWallet_id());
		this.setOrderNo(po.getOrder_no());
		this.setAmount(po.getAmount());
		this.setType(po.getType());
		this.setResultType(po.getResulttype());
		this.setDescription(po.getDescription());
		this.setCreateTime(po.getCreatetime());
	}
	
	public String getTypeText(){
		switch(getType()){
		case 1:
			return "充值";
		case 2:
			return "转入";
		case 3:
			return "转出";
		case 4:
			return "余额支付";
		default:
			return "";
		}
	}
	
	public String getResultTypeText(){
		switch(getResultType()){
		case 1:
			return "成功";
		case 2:
			return "失败";
		default:
			return "";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
