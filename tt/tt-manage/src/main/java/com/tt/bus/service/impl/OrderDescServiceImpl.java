package com.tt.bus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.bus.mapper.OrderDescMapper;
import com.tt.bus.service.OrderDescService;
import com.tt.pojo.Order;
@Service
public class OrderDescServiceImpl implements OrderDescService{

	@Autowired
	private OrderDescMapper OrderDescMapper;

}
