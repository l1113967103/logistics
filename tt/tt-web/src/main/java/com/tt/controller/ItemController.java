package com.tt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tt.pojo.Order;
import com.tt.service.ItemService;
/**
 * 订单的查询
 * @author Administrator
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{OrderDistrId}")
	public String findOrderDistrById(@PathVariable Integer orderDistrId,
			Model model) {
		//根据商品id号查询数据
		Order order = itemService.findOrderDistrById(orderDistrId);
		//将查询到的数据保存到request域中
		//页面通过el表达式获取
		model.addAttribute("orderDistrId", orderDistrId);
		return "about"; //跳转商品展现页面
	}
}
