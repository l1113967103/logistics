package com.tt.trans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Vehicle;
import com.tt.trans.service.VehicleService;

@Controller
@RequestMapping("/trans")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	@RequestMapping("/doVehicleListUI")
	public String doVehicleListUI() {
		return "trans/trans_order";
	}
	
	//分页查询
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {

		PageObject<Vehicle> pageObject=vehicleService.findPageObjects(username,pageCurrent);
		System.out.println(pageObject.getPageSize());
		return new JsonResult(pageObject);
	}
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer...ids) {
		int rows=vehicleService.deleteVehicle(ids);
		return new JsonResult("delete ok,rows="+rows);
	}
	/**跳转新增界面*/
	@RequestMapping("/doAddPage")
	public String doAddPage() {
		return "trans/trans_edit";
	}
	
}
