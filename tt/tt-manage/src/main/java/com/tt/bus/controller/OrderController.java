package com.tt.bus.controller;

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
	
	@RequestMapping("/doOrderListUI")
	 public String doUserListUI() {
		 return "bus/order_list";
	 }
	
	/**分页查询订单*/
	@RequestMapping("/findAllOrder")
	@ResponseBody
	public JsonResult findAllOrder(String orderNumber,Integer pageCurrent){
		PageObject<Order> pageObject = orderService.findAllOrder(orderNumber, pageCurrent);
		return new JsonResult(pageObject);
	}
	/**跳转到修改页面*/
//	@RequestMapping("doFindObjectById")
//	 @ResponseBody
//	 public JsonResult doFindObjectById(Integer id) {
//		 return new JsonResult(
//		 sysUserService.findObjectById(id));
//	 }
	
	/**审核订单*/
	@RequestMapping("/verifyOrder")
	public String verifyOrder(Integer id,Integer status) {
		orderService.verifyOrder(id,status);
		//重定向到查询订单页面
		return "redirect:/order/doOrderListUI";
	}
}
