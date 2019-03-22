package com.chinasofti.lexian.manager.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.order.constant.OrderConstant;
import com.chinasofti.lexian.manager.order.dao.OrderDao;
import com.chinasofti.lexian.manager.order.po.OrderPo;
import com.chinasofti.lexian.manager.order.service.OrderService;
import com.chinasofti.lexian.manager.order.vo.OrderItemVo;
import com.chinasofti.lexian.manager.order.vo.OrderQueryVo;
import com.chinasofti.lexian.manager.order.vo.OrderVo;

@Service
@Transactional
public class OrderServiceImpl extends BaseService implements OrderService {
	private OrderDao orderDao;
   
	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@Override
	public ResultHelper findOrders(OrderQueryVo queryVo) {
		List<OrderPo> pos = orderDao.findOrders(queryVo);
		List<OrderVo> vos = new ArrayList<OrderVo>();
		for(OrderPo po : pos){
			OrderVo vo = new OrderVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, OrderConstant.success,
				vos, queryVo.getTotal());
	}

	@Override
	public ResultHelper findOrder(String orderNo) {
		OrderPo po = orderDao.findOrder(orderNo);
		OrderVo vo = new OrderVo(po);
		List<OrderItemVo> itemsVo = orderDao.findOrderItems(vo.getId());
		vo.setOrderItems(itemsVo);
		
		return new ResultHelper(Constant.success_code, OrderConstant.success, vo);
	}

	@Override
	public ResultHelper deliverOrder(String orderNo) {
		OrderPo po = orderDao.findOrder(orderNo);
		po.setStates(3);   // 已发货状态
		orderDao.updateStates(po);
		
		return new ResultHelper(Constant.success_code, OrderConstant.success); 
	}
}
