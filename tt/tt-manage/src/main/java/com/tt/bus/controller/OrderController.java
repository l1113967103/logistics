package com.tt.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tt.bus.service.OrderService;
import com.tt.common.vo.SysResult;
import com.tt.pojo.Order;
import com.tt.service.DubboOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Reference(timeout = 3000,check = false)
	private DubboOrderService dubboOrderService;
	@Autowired
	private OrderService orderService;
	
	/**分页查询订单*/
	@RequestMapping("/findAllOrder")
	@ResponseBody
	public List<Order> findAllOrder(Integer orderDescId,Integer pageCurent,Model model){
		List<Order> orderList = orderService.findAllOrder(orderDescId, pageCurent);
		model.addAttribute("orderList", orderList);
		return orderList;
	}
	/**查询订单*/
	@RequestMapping("/findOrder")
	public String findOrder(Integer orderDescId,Model model) {
		Order order = orderService.findOrder(orderDescId);
		model.addAttribute("order", order);
		return "";
	}
	/**审核订单*/
	@RequestMapping("/verifyOrder")
	public String verifyOrder(Order order) {
		int flag = orderService.verifyOrder(order);
		if(flag==2) {
			dubboOrderService.backOrder(SysResult.fail("订单书写内容不规范",order));
			//不规范的订单是否要删除
		}
		//重定向到查询订单页面
		return "redirect:/order/findAllOrder";
	}
}
