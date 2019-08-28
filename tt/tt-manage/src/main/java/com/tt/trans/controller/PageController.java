package com.tt.trans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trans")
public class PageController {

	/**车辆管理*/
	@RequestMapping("/doVehicleListUI")
	public String doVehicleListUI() {
		return "trans/vehicle_list";
	}
	/**司机管理*/
	/**运输单管理*/
}
