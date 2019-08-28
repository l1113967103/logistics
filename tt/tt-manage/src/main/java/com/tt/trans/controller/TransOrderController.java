package com.tt.trans.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tt.trans.service.TransOrderService;

@Controller
@RequestMapping("/trans")
public class TransOrderController {

	@Autowired
	private TransOrderService transOrderService;
	
	@RequestMapping("/doTransOrderListUI")
	public String doTransOrderListUI() {
		return "trans/trans_list";
	}
	@RequestMapping("/orderRequest")
	@ResponseBody
	public Map<String,String> orderRequest(
			@Param("outSel") String outVal,
			@Param("driverSel") String driverVal,
			@Param("vehicleSel") String vehicleVal) {
		System.out.println("outVal="+outVal);
		System.out.println("driverVal="+driverVal);
		System.out.println("vehicleVal="+vehicleVal);
		
		Map<String,String> map = new HashMap<>();
		map.put("outVal", outVal);
		map.put("driverVal", driverVal);
		map.put("vehicleVal", vehicleVal);
		map.put("status", "1");
		
		return map;
	}
}
