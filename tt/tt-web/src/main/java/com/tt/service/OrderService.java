package com.tt.service;

import com.tt.pojo.Order;

public interface OrderService {

	String saveOrder(Order order);

	Order findOrderById(String id);

}
