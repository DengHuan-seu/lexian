package com.chinasofti.lexian.manager.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.manager.common.dao.BaseDao;
import com.chinasofti.lexian.manager.order.dao.OrderDao;
import com.chinasofti.lexian.manager.order.po.OrderPo;
import com.chinasofti.lexian.manager.order.vo.OrderItemVo;
import com.chinasofti.lexian.manager.order.vo.OrderQueryVo;

@Repository
public class OrderDaoImpl extends BaseDao implements OrderDao{
	@Override
	public List<OrderPo> findOrders(OrderQueryVo vo) {
		return selectList("findOrders", vo);
	}

	@Override
	public OrderPo findOrder(String orderNo) {
		return selectOne("findOrder", orderNo);
	}

	@Override
	public List<OrderItemVo> findOrderItems(int orderId) {
		return selectList("findOrderItems", orderId);
	}

	@Override
	public void updateStates(OrderPo po) {
		update("updateStates", po);
	}
	
}
