package com.chinasofti.lexian.mall.order.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.mall.common.controller.BaseController;
import com.chinasofti.lexian.mall.order.service.OrderService;
import com.chinasofti.lexian.mall.order.vo.OrderVo;
import com.chinasofti.lexian.mall.wallet.vo.WalletPayVo;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	private OrderService orderService;

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// 创建新订单
	@RequestMapping("/addOrder.do")
	@ResponseBody
	public Object addOrder(OrderVo vo) {
		try {
			vo.setPaymentType(URLDecoder.decode(vo.getPaymentType(), "utf-8"));
			vo.setDeliveryType(URLDecoder.decode(vo.getDeliveryType(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return orderService.addOrder(vo);

	}

	// 查看某个用户的所有订单列表
	@RequestMapping("/findOrders.do")
	@ResponseBody
	public Object findOrders(OrderVo vo) {
		return orderService.findOrders(vo);
	}

	// 用钱包支付，并且修改订单付款状态
	@RequestMapping("/updateOrderPay.do")
	@ResponseBody
	public Object updateOrderPay(WalletPayVo vo) {
		return orderService.updateOrderPay(vo);
	}
	
	// 修改订单付款状态
	@RequestMapping("/completeOrder.do")
	@ResponseBody
	public Object completeOrder(String orderNo) {
		return orderService.completeOrder(orderNo);
	}
	
	// 返回单个订单详情
	@RequestMapping("/findOrderInfo.do")
	@ResponseBody
	public Object findOrderInfo(String orderNo){
		return orderService.findOrderInfo(orderNo);
	}
	
	// 返回单个订单基本情况
	@RequestMapping("/findOrderSimple.do")
	@ResponseBody
	public Object findOrderSimple(String orderNo){
		return orderService.findOrderSimple(orderNo);
	}
	
	// 从订单中获取商品的销量排行
	@RequestMapping("/findTopCommodities.do")
	@ResponseBody
	public Object findTopCommodities(){
		return orderService.findTopCommodities();
	}
}
