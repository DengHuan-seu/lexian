package com.chinasofti.lexian.mall.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.common.dao.BaseDao;
import com.chinasofti.lexian.mall.order.dao.OrderDao;
import com.chinasofti.lexian.mall.order.po.OrderItemPo;
import com.chinasofti.lexian.mall.order.po.OrderPo;
import com.chinasofti.lexian.mall.order.po.TopSalesCommodityPo;
import com.chinasofti.lexian.mall.order.vo.OrderItemVo;

@Repository
public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public List<TopSalesCommodityPo> findTopCommodities() {
		return selectList("findTopCommodities");
	}

	@Override
	public List<OrderItemPo> getItemsFromTrolley(String[] ids) {
		return selectList("getItemsForTrolley", ids);
	}

	@Override
	public void addOrder(OrderPo po) {
		insert("addOrder", po);
	}

	@Override
	public void addOrderItem(OrderItemPo item) {
		insert("addOrderItem", item);
	}

	@Override
	public void updateStock(OrderItemPo item) {
		update("updateStock", item);
	}

	@Override
	public OrderPo findOrderSimple(String orderNo) {
		return selectOne("findOrderSimple", orderNo);
	}

	@Override
	public void updateOrderPay(OrderPo po) {
		update("updateOrderPay", po);
	}

	@Override
	public List<OrderPo> findOrders(OrderPo po) {
		return selectList("findOrders", po);
	}

	@Override
	public List<OrderItemVo> findOrderItems(int orderId) {
		return selectList("findOrderItems", orderId);
	}

	@Override
	public void updateOrderStates(OrderPo orderPo) {
		update("updateOrderStates", orderPo);
	}

}
