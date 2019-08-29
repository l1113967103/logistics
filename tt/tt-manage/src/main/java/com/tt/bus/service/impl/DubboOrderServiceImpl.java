package com.tt.bus.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.tt.bus.mapper.OrderDescMapper;
import com.tt.bus.mapper.OrderMapper;
import com.tt.pojo.Order;
import com.tt.pojo.OrderDesc;
import com.tt.service.DubboOrderService;

public class DubboOrderServiceImpl implements DubboOrderService{
	
	private OrderMapper orderMapper;
	
	private OrderDescMapper orderDescMapper;

	/**根据货物id查询order(订单)信息*/
	@Override
	public Order findOrderByOrderNumber(String orderNumber) {
		Order order = orderMapper.findOrderByOrderNumber(orderNumber);
		if(order==null||"".equals(order.getOrderNumber()))
			throw new RuntimeException("没有该货物的订单信息");
		return order;
	}

	//新增订单信息,同时新增货物信息
	@Override
	@Transactional
	public Map<Integer,String> addOrder(Order order) {
		int row = 0;
		Map<Integer,String> map = new HashMap<>();
		try {
			String orderNumber = UUID.randomUUID().toString().replaceAll("-", "");
			order.setStatus(0).setCreatedTime(new Date()).setModifiedTime(new Date());
			//显示货物未审核
			row = orderMapper.insert(order);
			OrderDesc orderDesc = orderDescMapper.findOrderIdByOrderDesc(order.getId());
			orderDesc.setStatus(0);//显示货物未入库
			int row1 = orderDescMapper.insert(orderDesc);
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
