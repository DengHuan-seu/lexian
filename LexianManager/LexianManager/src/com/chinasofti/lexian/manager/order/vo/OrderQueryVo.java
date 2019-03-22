package com.chinasofti.lexian.manager.order.vo;

import java.util.Date;

import com.chinasofti.lexian.manager.common.util.PageHelper;

public class OrderQueryVo extends PageHelper<OrderQueryVo> {
	private String orderNo;
	private Date dateFrom;
	private Date dateTo;
	private int states;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
}
