package com.tt.bus.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.bus.service.OrderService;
import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Order;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("doOrderListUI")
	 public String doUserListUI() {
		 return "sys/order_list";
	 }
	
	/**分页查询订单*/
	@RequestMapping("/findAllOrder")
	@ResponseBody
	public JsonResult findAllOrder(String orderNumber,Integer pageCurent){
		PageObject<Order> pageObject = orderService.findAllOrder(orderNumber, pageCurent);
		return new JsonResult(pageObject);
	}
	
	/**审核订单*/
	@RequestMapping("/verifyOrder")
	public String verifyOrder(Order order) {
		int flag = orderService.verifyOrder(order);
		if(flag==2) {
			//设置不通过的审核时间，不规范的订单是否要删除
			order.setModifiedTime(new Date());
		}
		//重定向到查询订单页面
		return "redirect:/order/findAllOrder";
	}
}
