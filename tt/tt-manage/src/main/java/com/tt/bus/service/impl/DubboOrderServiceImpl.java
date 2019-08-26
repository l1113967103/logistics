package com.tt.bus.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.tt.bus.mapper.OrderDescMapper;
import com.tt.bus.mapper.OrderMapper;
import com.tt.pojo.Order;
import com.tt.service.DubboOrderService;

public class DubboOrderServiceImpl implements DubboOrderService{
	
	private OrderMapper orderMapper;
	
	private OrderDescMapper orderDescMapper;

	/**根据商品id查询order(订单)信息*/
	@Override
	public Order findOrderByOrderNumber(Integer orderNumber) {
		Order order = orderMapper.findOrderByOrderNumber(orderNumber);
		if(order==null||"".equals(order.getOrderNumber()))
			throw new RuntimeException("没有该商品的订单信息");
		return order;
	}

	//新增订单信息,同时新增商品信息
	@Override
	@Transactional
	public Map<Integer,String> addOrder(Order order) {
		int row = 0;
		Map<Integer,String> map = new HashMap<>();
		try {
			String orderNumber = UUID.randomUUID().toString().replaceAll("-", "");
			order.setOrderNumber(orderNumber).setCreateTime(new Date()).setModifiedTime(new Date());
			row = orderMapper.insert(order);
			int row1 = orderDescMapper.insert(order.getOrderDesc());
			if(row==0||row1==0)
				throw new RuntimeException("订单添加失败");
			map.put(1, orderNumber);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(0, "null");
		}
		return map;
	}

}
