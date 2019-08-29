package com.tt.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.bus.service.OrderDescService;
import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.pojo.OrderDesc;

@Controller
@RequestMapping("/orderDesc")
public class OrderDescController {
	
	@Autowired
	private OrderDescService orderDescService;

	@RequestMapping("/findOrderDescByPage")
	@ResponseBody
	public JsonResult findOrderDescByPage(Integer pageCurrent) {
		PageObject<OrderDesc> descByPage = orderDescService.findOrderDescByPage(pageCurrent);
		return new JsonResult(descByPage);
	}
}
