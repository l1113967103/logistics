package com.tt.bus.service;

import com.tt.pojo.Order;

public interface OrderService {

	/**根据商品id查询order(订单)信息*/
	Order findOrder(Integer orderDescId);
}
