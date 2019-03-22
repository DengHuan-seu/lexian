package com.chinasofti.lexian.mall.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.mall.commodity.dao.CommodityDao;
import com.chinasofti.lexian.mall.common.service.BaseService;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.OrderNumberGenerator;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.order.constant.OrderConstant;
import com.chinasofti.lexian.mall.order.dao.OrderDao;
import com.chinasofti.lexian.mall.order.po.OrderItemPo;
import com.chinasofti.lexian.mall.order.po.OrderPo;
import com.chinasofti.lexian.mall.order.po.TopSalesCommodityPo;
import com.chinasofti.lexian.mall.order.service.OrderService;
import com.chinasofti.lexian.mall.order.vo.OrderItemVo;
import com.chinasofti.lexian.mall.order.vo.OrderVo;
import com.chinasofti.lexian.mall.order.vo.TopSalesCommodityVo;
import com.chinasofti.lexian.mall.wallet.dao.WalletDao;
import com.chinasofti.lexian.mall.wallet.po.WalletPo;
import com.chinasofti.lexian.mall.wallet.po.WalletRecordPo;
import com.chinasofti.lexian.mall.wallet.vo.WalletPayVo;

@Service
@Transactional
public class OrderServiceImpl extends BaseService implements OrderService {
	private OrderDao orderDao;
	private CommodityDao commodityDao;
	private WalletDao walletDao;

	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@Autowired
	public void setCommodityDao(CommodityDao commodityDao){
		this.commodityDao = commodityDao;
	}
	
	@Autowired
	public void setWalletDao(WalletDao walletDao){
		this.walletDao = walletDao;
	}

	@Override
	public ResultHelper findTopCommodities() {
		List<TopSalesCommodityPo> pos = orderDao.findTopCommodities();
		List<TopSalesCommodityVo> vos = new ArrayList<TopSalesCommodityVo>();
		for(TopSalesCommodityPo po : pos){
			TopSalesCommodityVo vo = new TopSalesCommodityVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, OrderConstant.success, vos);
	}

	@Override
	public ResultHelper addOrder(OrderVo vo) {
		String[] ids = vo.getTrolleyIds().split(",");
		
		String orderNo = OrderNumberGenerator.generateOrderNo();
		String userId = getUser().getId();
		
		// 获取所有商品信息
		List<OrderItemPo> items = orderDao.getItemsFromTrolley(ids);
		double totalAmount = 0.0;
		for(OrderItemPo item : items){
			item.setStore_no(vo.getStoreNo());
			totalAmount += item.getListprice() * item.getAmount();
		}
		
		// 写入orders表
		OrderPo po = new OrderPo();
		po.setOrder_no(orderNo);
		po.setUser_id(userId);
		po.setPaymenttype(vo.getPaymentType());
		po.setDeliverytype(vo.getDeliveryType());
		po.setStore_no(vo.getStoreNo());
		po.setTotalamount(totalAmount);
		orderDao.addOrder(po);
		int orderId = po.getId();
		
		// 写入orderitem表，并且更新商品库存
		for(OrderItemPo item : items){
			item.setOrder_id(orderId);
			orderDao.addOrderItem(item);
			orderDao.updateStock(item);
		}
		
		// 删除Trolley中的购物项
		commodityDao.deleteTrolley(ids);
		
		return new ResultHelper(Constant.success_code, OrderConstant.success, orderNo);
	}

	@Override
	public ResultHelper findOrderSimple(String orderNo) {
		OrderPo po = orderDao.findOrderSimple(orderNo);
		OrderVo vo = new OrderVo(po);
		return new ResultHelper(Constant.success_code, OrderConstant.success, vo);
	}

	@Override
	public ResultHelper updateOrderPay(WalletPayVo vo) {
		// 获取用户当前钱包中的余额
		String userId = getUser().getId();
		WalletPo wallet = walletDao.findWallet(userId);
		
		// 获取订单应支付金额
		OrderPo order = orderDao.findOrderSimple(vo.getOrderNo());
		
		// 检查余额是否足够
		if(wallet.getBalance() <= order.getTotalamount()){
			return new ResultHelper(Constant.failed_code, OrderConstant.payment_outofbalance);
		}
		
		// 执行付款操作
		OrderPo po = new OrderPo();
		po.setOrder_no(vo.getOrderNo());
		po.setStates(2);	// 2代表已付款
		po.setPaymentsubtype("余额支付");
		orderDao.updateOrderPay(po);
		
		// 扣除余额
		wallet.setBalance(wallet.getBalance() - order.getTotalamount());
		wallet.setPassword(vo.getPassword());
		int result = walletDao.updateWallet(wallet);
		if(result != 1){			// 支付失败(因为密码错误)
			return new ResultHelper(Constant.failed_code, OrderConstant.payment_invalidpassword);
		}
		
		// 添加钱包记录
		WalletRecordPo record = new WalletRecordPo();
		record.setWallet_id(wallet.getId());
		record.setOrder_no(vo.getOrderNo());
		record.setType(4);      		// 4代表余额支付
		record.setResulttype(1);;  		// 1代表成功
		record.setAmount(order.getTotalamount());
		record.setDescription("使用钱包余额购物");
		walletDao.addWalletRecord(record);
		
		return new ResultHelper(Constant.success_code, OrderConstant.success);
	}

	@Override
	public ResultHelper findOrders(OrderVo vo) {
		String userId = getUser().getId();
		OrderPo po = new OrderPo();
		po.setUser_id(userId);
		po.setStates(vo.getStates());
		po.setPageSize(vo.getPageSize());
		po.setPageNo(vo.getPageNo());
		
		List<OrderPo> pos = orderDao.findOrders(po);
		List<OrderVo> vos = new ArrayList<OrderVo>();
		for(OrderPo p : pos){
			OrderVo v = new OrderVo(p);
			vos.add(v);
		}
		
		return new ResultHelper(Constant.success_code, OrderConstant.success, 
				vos, po.getTotalSize());
	}

	@Override
	public ResultHelper findOrderInfo(String orderNo) {
		String userId = getUser().getId();
		// 获取订单总体信息
		OrderPo orderPo = orderDao.findOrderSimple(orderNo);
		if(!orderPo.getUser_id().equals(userId)){
			return new ResultHelper(Constant.success_code, OrderConstant.order_notfound);
		}
		OrderVo orderVo = new OrderVo(orderPo);
		
		// 获取订单项
		List<OrderItemVo> orderItems = orderDao.findOrderItems(orderPo.getId());
		orderVo.setOrderItems(orderItems);
		
		return new ResultHelper(Constant.success_code, OrderConstant.success,orderVo);
	}

	@Override
	public ResultHelper completeOrder(String orderNo) {
		String userId = getUser().getId();
		// 获取订单总体信息
		OrderPo orderPo = orderDao.findOrderSimple(orderNo);
		if(!orderPo.getUser_id().equals(userId)){
			return new ResultHelper(Constant.success_code, OrderConstant.order_notfound);
		}
		orderPo.setStates(4);   // 代表已经完成的订单
		orderDao.updateOrderStates(orderPo);;
		return new ResultHelper(Constant.success_code, OrderConstant.success);
	}
}
