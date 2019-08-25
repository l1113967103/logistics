package com.tt.bus.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tt.bus.mapper.OrderDescMapper;
import com.tt.bus.mapper.OrderMapper;
import com.tt.bus.service.OrderService;
import com.tt.pojo.Order;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDescMapper orderDescMapper;

	/**根据分页查询订单信息*/
	@Override
	public List<Order> findAllOrder(Integer orderDescId, Integer pageCurent) {
		// TODO Auto-generated method stub
		return null;
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
			order.setCreateTime(new Date()).setModifiedTime(order.getCreateTime());
			row = orderMapper.insert(order);
			int row1 = orderDescMapper.insert(order.getOrderDesc());
			if(row==0||row1==0)
				throw new RuntimeException("订单添加失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	//审核订单0.未审核1.通过2.不通过
	@Override
	@Transactional
	public int verifyOrder(Order order) {
		if(order.getStatus()==1) {
			Order order2 = orderMapper.selectById(order);
			if(order2==null)
				throw new RuntimeException("没有该订单信息");
			if(order2.getStatus()==order.getStatus())
				throw new RuntimeException("请审核订单");
			int row = orderMapper.updateById(order);
			if(row==0)
				throw new RuntimeException("审核订单失败");
			return 1;
		}else if(order.getStatus()==2){
			//交给用户重新填写订单
			return 2;
		}else {
			return 0;
		}
	}

}
