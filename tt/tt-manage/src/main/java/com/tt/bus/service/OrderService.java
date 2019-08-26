package com.tt.bus.service;

import java.util.List;

import com.tt.common.vo.PageObject;
import com.tt.pojo.Order;

public interface OrderService {

	/**分页查询订单*/
	PageObject<Order> findAllOrder(Integer orderNumber,Integer pageCurrent);
	
	/**审核订单 */
	int verifyOrder(Order order);
	
	/**查询订单，为了生成订单时使用,生成入库*/
	Order findOrder(Integer orderDescId);
}
