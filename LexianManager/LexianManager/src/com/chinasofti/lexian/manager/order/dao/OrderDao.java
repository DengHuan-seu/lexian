package com.chinasofti.lexian.manager.order.dao;

import java.util.List;

import com.chinasofti.lexian.manager.order.po.OrderPo;
import com.chinasofti.lexian.manager.order.vo.OrderItemVo;
import com.chinasofti.lexian.manager.order.vo.OrderQueryVo;

public interface OrderDao {
	List<OrderPo> findOrders(OrderQueryVo vo);

	OrderPo findOrder(String orderNo);

	List<OrderItemVo> findOrderItems(int orderId);

	void updateStates(OrderPo po);
}
