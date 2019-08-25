package com.tt.bus.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.bus.mapper.OrderDescMapper;
import com.tt.bus.mapper.OrderMapper;
import com.tt.bus.service.OrderService;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Order;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDescMapper orderDescMapper;

	/**根据分页查询订单信息*/
	@Override
	public List<Order> findAllOrder(Integer orderNumber, Integer pageCurrent) {
		QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
		Integer pageSize=5;//每页5条数据
		Integer startIndex = (pageCurrent-1)*pageSize;
		//查询订单总数量
		Integer count = 0;
		/**当前页要呈现的记录*/
		List<Order> records = null;
		if(null==orderNumber||"".equals(orderNumber)) {
			count = orderMapper.selectCount(null);//查询所有订单数量
			if(count==0)
				throw new RuntimeException("没有订单信息");
			records = orderMapper.selectOrderNumberByPage(startIndex, pageSize);
		}else {
			queryWrapper.like("order_number", orderNumber);
			count = orderMapper.selectCount(queryWrapper);//根据汽车名称查询所有汽车数量
			if(count==0)
				throw new RuntimeException("没有车辆信息");
			records = vehicleMapper.selectVehicleByName(username,startIndex, pageSize);
		}
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
		return null;
	}

	@Override
	public int delOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int updateOrder(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}


	/**根据商品id查询order(订单)信息*/
	@Override
	public Order findOrder(Integer orderDescId) {
		Order order = orderMapper.findOrderByOrderDescId(orderDescId);
		if(order==null)
			throw new RuntimeException("没有该商品的订单信息");
		return order;
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
