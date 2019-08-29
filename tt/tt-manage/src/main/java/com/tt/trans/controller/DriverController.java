package com.tt.trans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Driver;
import com.tt.trans.service.DriverService;

@Controller
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;
	/**分页查询*/
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		PageObject<Driver> pageObject = driverService.findPageObjects(username, pageCurrent);
		return new JsonResult(pageObject);
	}
	/**跳转新增页面*/
	@RequestMapping("/doScenicSpotEditUI")
	public String doScenicSpotEditUI() {
		return "trans/driver_edit";
	}

	/**跳转编辑页面*/
	@RequestMapping("/doDriverEditUI")
	public String doMenuEditUI(){
		return "trans/driver_edit";
	}
	
	/**删除司机信息*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody 
	public JsonResult doDeleteObject(Integer id) {
		driverService.delDriver(id); 
		return new JsonResult("delete ok"); 
	}


	/**新增司机信息*/
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Driver driver) {
		driverService.addDriver(driver);
		return new JsonResult("insert ok");
	}
	
	/**修改司机信息*/
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doFindObjectById(Driver driver) {
		driverService.updateDriver(driver);
		return new JsonResult("update ok");
	}

	/**生成运输单时，需要下拉框中的订单号*/
	@RequestMapping("/doFindAllDriver")
	@ResponseBody
	public JsonResult doFindAllDriver() {
		List<Driver> driverList = driverService.findAllDriver();
		return new JsonResult(driverList);
	}
	


}
