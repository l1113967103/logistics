package com.tt.trans.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tt.bus.service.OrderDescService;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Storage;
import com.tt.pojo.Vehicle;
import com.tt.rep.service.StorageService;
import com.tt.trans.service.VehicleService;

@Controller
@RequestMapping("/trans")
public class PageTransController {

	@Autowired
	private StorageService storageService;
	@Autowired
	private OrderDescService orderDescService;
	@Autowired
	private VehicleService vehicleService;
	/**车辆管理*/
	@RequestMapping("/doVehicleListUI")
	public String doVehicleListUI() {
		return "trans/vehicle_list";
	}
	/**司机管理*/
	@RequestMapping("/doDriverListUI")
	public String doDriverListUI() {
		return "trans/driver_list";
	}
	/**运输单管理*/
	@RequestMapping("/doTransOrderListUI")
	public String doTransOrderListUI(Model model) {
//		List<Storage> allStorage = storageService.findAllStorage();
//		List<OrderDesc> allOrderDesc = orderDescService.findAllOrderDesc();
//		List<Vehicle> allVehicle = vehicleService.findAllVehicle();
//		Map<String,Object> map = new HashMap<>();
//		map.put("allStorage", allStorage);
//		map.put("allOrderDesc", allOrderDesc);
//		map.put("allVehicle", allVehicle);
//		System.out.println(map);
//		model.addAllAttributes(map);
//		List<Storage> list = storageService.findAllStorage();
//		model.addAttribute("list",list);
//		return "test";
		return "trans/trans_list";
	}
	
}
