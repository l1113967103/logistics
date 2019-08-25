package com.tt.bus.service;

import java.util.List;

import com.tt.pojo.Order;

public interface OrderService {

	/**分页查询订单*/
	List<Order> findAllOrder(Integer orderNumber,Integer pageCurrent);
	
	/**审核订单 */
	int verifyOrder(Order order);
	/**删除订单*/
	int delOrder(Integer orderId);
	/**修改订单*/
	int updateOrder(Order order);
	/**查询订单，为了生成订单时使用*/
	Order findOrder(Integer orderDescId);
}
