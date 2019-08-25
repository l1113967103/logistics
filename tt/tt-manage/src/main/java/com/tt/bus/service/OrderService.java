package com.tt.bus.service;

import java.util.List;

import com.tt.pojo.Order;

public interface OrderService {

	/**分页查询订单*/
	List<Order> findAllOrder(Integer orderDescId,Integer pageCurent);
	/**根据商品id查询order(订单)信息*/
	Order findOrder(Integer orderDescId);
	/**添加订单信息*/
	int addOrder(Order order);
	/**审核订单 */
	int verifyOrder(Order order);
	/***/
}
