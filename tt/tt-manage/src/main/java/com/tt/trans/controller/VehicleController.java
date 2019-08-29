package com.tt.trans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Vehicle;
import com.tt.trans.service.VehicleService;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	//分页查询
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {

		PageObject<Vehicle> pageObject=vehicleService.findPageObjects(username,pageCurrent);
		System.out.println(pageObject.getPageSize());
		return new JsonResult(pageObject);
	}
	
	/**跳转编辑页面*/
	
	  @RequestMapping("/doVehicleEditUI") 
	  public String doMenuEditUI(){ 
		  return  "trans/vehicle_edit";
		  }
	 
	/**删除数据*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer id) {
		System.out.println(id);
		int rows=vehicleService.deleteVehicle(id);
		return new JsonResult("delete ok,rows="+rows);
	}
	
	/**根据id查询车辆信息，为了修改时的数据回显*/
	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(vehicleService.findObjectById(id));
	}
	/**新增车辆信息*/
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Vehicle vehicle) {
		vehicleService.addVehicle(vehicle);
		return new JsonResult("insert ok");
	}
	/**修改车辆信息*/
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Vehicle vehicle) {
		vehicleService.updateVehicle(vehicle);
		return new JsonResult("update ok");
	}
	
	/**生成运输单时，需要下拉框中的订单号*/
	@RequestMapping("/doFindAllVehicle")
	@ResponseBody
	public JsonResult doFindAllVehicle() {
		List<Vehicle> vehicleList = vehicleService.findAllVehicle();
		return new JsonResult(vehicleList);
	}
}
