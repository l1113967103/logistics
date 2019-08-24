package com.tt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.tt.common.vo.JsonResult;
import com.tt.common.vo.SysResult;
import com.tt.pojo.Order;
import com.tt.service.OrderService;
import com.tt.util.UserThreadLocal;
/**
 * 	实现业务的预定模块
 * @author Administrator
 *
 */
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	/**
	 *  点击提交按钮时，如果OrderId不为空则提交成功，页面返回到订单查询页面（about）
	 *  如果OrderId为空则提交失败，页面不返回
	 * @param order
	 * @return
	 */
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order) {
		try {
			Integer userId = UserThreadLocal.getUser().getId();
			order.setId(userId);
			//当订单入库时需要返回订单ID号
			String orderId = orderService.saveOrder(order);
			if(!StringUtils.isEmpty(orderId)) {

				return SysResult.ok(orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.fail();
	}
	@RequestMapping("/success")
	//根据orderId查询订单数据
	public String findOrderById(String id,Model model) {
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "success";
	}
}
