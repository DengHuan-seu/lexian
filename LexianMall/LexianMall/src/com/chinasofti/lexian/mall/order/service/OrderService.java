package com.chinasofti.lexian.mall.order.service;

import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.order.vo.OrderVo;
import com.chinasofti.lexian.mall.wallet.vo.WalletPayVo;

public interface OrderService {
	ResultHelper findTopCommodities();

	ResultHelper addOrder(OrderVo vo);

	ResultHelper findOrderSimple(String orderNo);

	ResultHelper updateOrderPay(WalletPayVo vo);

	ResultHelper findOrders(OrderVo vo);

	ResultHelper findOrderInfo(String orderNo);

	ResultHelper completeOrder(String orderNo);
}
