package com.tt.bus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.bus.mapper.OrderMapper;
import com.tt.bus.service.OrderService;
import com.tt.pojo.Order;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;

	/**根据商品id查询order(订单)信息*/
	@Override
	public Order findOrder(Integer orderDescId) {
		Order order = orderMapper.findOrderByOrderDescId(orderDescId);
		if(order==null)
			throw new RuntimeException("没有该商品的订单信息");
		return order;
	}
}
