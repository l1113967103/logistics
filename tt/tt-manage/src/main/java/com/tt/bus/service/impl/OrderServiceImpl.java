package com.tt.bus.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.bus.mapper.OrderMapper;
import com.tt.bus.service.OrderService;
import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.Order;
import com.tt.util.ObjectThreadLocal;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;

	/**根据分页查询订单信息*/
	@Override
	public PageObject<Order> findAllOrder(String orderNumber, Integer pageCurrent) {
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
				throw new ServiceException("没有订单信息");
			records = orderMapper.findOrderNumberByPage(startIndex, pageSize);
		}else {
			queryWrapper.like("order_number", orderNumber);
			count = orderMapper.selectCount(queryWrapper);//根据订单编号查询所有订单数量
			if(count==0)
				throw new ServiceException("没有订单信息");
			records = orderMapper.findOrderNumberByPage(orderNumber,startIndex, pageSize);
		}
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}

	//审核订单0.未审核1.通过2.不通过
	@Override
	@Transactional
	public int verifyOrder(Order order) {
		if(order.getStatus()==1) {
			Order order2 = orderMapper.selectById(order);
			if(order2==null)
				throw new ServiceException("没有该订单信息");
			if(order2.getStatus()==order.getStatus())
				throw new ServiceException("请审核订单");
			int row = orderMapper.updateById(order);
			if(row==0)
				throw new ServiceException("审核订单失败");
			//将审核通过的订单交给仓储部门进行商品入库，为了生成入库单
			ObjectThreadLocal.setObject(order);
			return 1;
		}else if(order.getStatus()==2){
			//交给用户重新填写订单发往web
			return 2;
		}else {
			return 0;
		}
	}
	/**查询订单，为了生成订单时使用,生成入库*/
	@Override
	public Order findOrder(Integer orderDescId) {
		Order order = orderMapper.findOrderByOrderDescId(orderDescId);
		System.err.println(order);
		if(order==null)
			throw new ServiceException("该商品无对应的订单");
		return order;
	}

}
