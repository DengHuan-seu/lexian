package com.chinasofti.lexian.mall.order.dao;

import java.util.List;

import com.chinasofti.lexian.mall.order.po.OrderItemPo;
import com.chinasofti.lexian.mall.order.po.OrderPo;
import com.chinasofti.lexian.mall.order.po.TopSalesCommodityPo;
import com.chinasofti.lexian.mall.order.vo.OrderItemVo;

public interface OrderDao {

	List<TopSalesCommodityPo> findTopCommodities();

	List<OrderItemPo> getItemsFromTrolley(String[] ids);

	void addOrder(OrderPo po);

	void addOrderItem(OrderItemPo item);

	void updateStock(OrderItemPo item);

	OrderPo findOrderSimple(String orderNo);

	void updateOrderPay(OrderPo po);

	List<OrderPo> findOrders(OrderPo po);

	List<OrderItemVo> findOrderItems(int orderId);

	void updateOrderStates(OrderPo orderPo);
}
