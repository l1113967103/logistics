package com.tt.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.tt.common.vo.JsonResult;
import com.tt.pojo.Order;
import com.tt.service.DubboOrderService;

/**
 * Business reservation  业务预定
 * 	思路：1.点击业务预定呈现业务预定的呈现页面
 * 		2.点击提交按钮，如果用户id不为空，则提交成功，页面跳转至首页
 * 					如果用户id为空，则提交失败，页面不跳转
 * @author Administrator
 *
 */
@Controller 
public class OrderController {
	@Reference(timeout = 3000,check = false)
	private DubboOrderService dubboOrderService;
	@RequestMapping("/create")
	public String orderPage() {
		return "about";
	}
	/**
	 * 	提交订单
	 * @param order
	 * @return
	 */
	@RequestMapping("/submit")
	@ResponseBody
	public JsonResult saveOrder(Order order) {
		try {
			//提交用户订单，返回影响值表示是否成功。0.失败1.成功
			Map<Integer, String> map = dubboOrderService.addOrder(order);
			Iterator<Integer> iter = map.keySet().iterator();
			if (iter.hasNext()) {
				//获取状态码。0表示添加失败。1.表示添加成功
				Integer status = iter.next();
				if(status==0||map.get(status)==null)
					throw new RuntimeException("新增订单失败");
				//此时status为1
				String orderNumber = map.get(status);
				if(StringUtils.isEmpty(orderNumber))
					throw new RuntimeException("提交订单失败");
				//返回订单号
				return new JsonResult(orderNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult("订单新增失败");
	}
	/**根据订单号查询订单*/
	@RequestMapping("")
	@ResponseBody
	public JsonResult findOrder(String orderNumber) {
		Order order = dubboOrderService.findOrderByOrderNumber(orderNumber);
		return new JsonResult(order);
	}
}
