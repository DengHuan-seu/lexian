package com.chinasofti.lexian.mall.order.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinasofti.lexian.mall.common.util.PageHelper;
import com.chinasofti.lexian.mall.order.po.OrderPo;

public class OrderVo  extends PageHelper<OrderVo>{
	private int id;
	private String orderNo;
	private String userId;
	private double totalAmount;
	private String storeNo;
	private String paymentType;
	private String paymentSubtype;
	private String deliveryType;
	private int states;
	private Date createTime;
	private String storeName;
	
	// 以,分隔的购物车项id
	private String trolleyIds;
	
	private List<OrderItemVo> orderItems;
	
	public OrderVo(){
	}
	
	public OrderVo(OrderPo po){
		this.id = po.getId();
		this.orderNo = po.getOrder_no();
		this.userId = po.getUser_id();
		this.totalAmount = po.getTotalamount();
		this.storeNo = po.getStore_no();
		this.paymentType = po.getPaymenttype();
		this.paymentSubtype = po.getPaymentsubtype();
		this.deliveryType = po.getDeliverytype();
		this.states = po.getStates();
		this.createTime = po.getCreatetime();
		this.storeName = po.getStorename();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	public String getTrolleyIds() {
		return trolleyIds;
	}
	public void setTrolleyIds(String trolleyIds) {
		this.trolleyIds = trolleyIds;
	}

	public String getPaymentSubtype() {
		return paymentSubtype;
	}

	public void setPaymentSubtype(String paymentSubtype) {
		this.paymentSubtype = paymentSubtype;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getStatesText(){
		switch(this.states){
		case 1:
			return "待付款";
		case 2:
			return "已付款";
		case 3:
			return "已发货";
		case 4:
			return "已签收";
		default:
			return "状态未知";
		}
	}

	public List<OrderItemVo> getOrderItems() {
		if(orderItems == null){
			orderItems = new ArrayList<OrderItemVo>();
		}
		return orderItems;
	}

	public void setOrderItems(List<OrderItemVo> orderItems) {
		this.orderItems = orderItems;
	}
}
