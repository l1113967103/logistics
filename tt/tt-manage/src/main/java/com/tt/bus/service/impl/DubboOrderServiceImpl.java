package com.tt.bus.service.impl;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.tt.bus.mapper.OrderDescMapper;
import com.tt.bus.mapper.OrderMapper;
import com.tt.common.vo.SysResult;
import com.tt.pojo.Order;
import com.tt.service.DubboOrderService;

public class DubboOrderServiceImpl implements DubboOrderService{
	
	private OrderMapper orderMapper;
	
	private OrderDescMapper orderDescMapper;

	@Override
	public void backOrder(SysResult fail) {
		//调用order项目，将信息反馈给用户，审核不通过
		
	}

	/**根据商品id查询order(订单)信息*/
	@Override
	public Order findOrder(Integer orderDescId) {
		Order order = orderMapper.findOrderByOrderDescId(orderDescId);
		if(order==null)
			throw new RuntimeException("没有该商品的订单信息");
		return order;
	}

	//新增订单信息,同时新增商品信息
	@Override
	@Transactional
	public int addOrder(Order order) {
		int row = 0;
		try {
			order.setCreateTime(new Date()).setModifiedTime(new Date());
			row = orderMapper.insert(order);
			int row1 = orderDescMapper.insert(order.getOrderDesc());
			if(row==0||row1==0)
				throw new RuntimeException("订单添加失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

}
