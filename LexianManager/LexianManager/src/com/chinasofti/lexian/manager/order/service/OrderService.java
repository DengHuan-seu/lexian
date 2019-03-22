package com.chinasofti.lexian.manager.order.service;

import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.order.vo.OrderQueryVo;

public interface OrderService {
	public ResultHelper findOrders(OrderQueryVo queryVo);

	public ResultHelper findOrder(String orderNo);

	public ResultHelper deliverOrder(String orderNo);
}
