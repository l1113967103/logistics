package com.tt.service;

import java.util.Map;

import com.tt.common.vo.SysResult;
import com.tt.pojo.Order;

public interface DubboOrderService {

	/**添加订单信息,添加成功返回订单号Map<Integer,String>,key为0,1表示是否成功，value为订单号*/
	Map<Integer,String> addOrder(Order order);
	/**根据货物订单号查询order(订单)信息*/
	Order findOrderByOrderNumber(String orderNumber);
	
	
}
