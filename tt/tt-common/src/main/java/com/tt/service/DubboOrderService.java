package com.tt.service;

import com.tt.common.vo.SysResult;
import com.tt.pojo.Order;

public interface DubboOrderService {

	/**返回错误订单，及其提示信息*/
	void backOrder(SysResult fail);
	/**添加订单信息*/
	int addOrder(Order order);
	
	/**根据商品id查询order(订单)信息*/
	Order findOrder(Integer orderDescId);
	
}
