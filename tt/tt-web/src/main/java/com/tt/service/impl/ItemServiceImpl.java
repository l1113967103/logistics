package com.tt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.pojo.Order;
import com.tt.service.ItemService;
import com.tt.util.HttpClientService;
import com.tt.util.ObjectMapperUtil;
/**
 * 订单的查询
 * @author Administrator
 *
 */

@Service 
public class ItemServiceImpl implements ItemService {
	@Autowired
	private HttpClientService httpClient;
	
	/**
	 * 	点击查询按钮开始从前台web向后台manage发起请求获取数据
	 */
	@Override
	public Order findOrderDistrById(Integer orderDistrId) {
		String url = "http://manage.tt.com/web/orderDistr/findOrderDistrById/"+orderDistrId;
		String result = httpClient.doGet(url);
		return ObjectMapperUtil.toObject(result, Order.class);
	}
	
	
}
