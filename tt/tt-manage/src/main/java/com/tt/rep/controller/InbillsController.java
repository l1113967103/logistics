package com.tt.rep.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Inbills;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Storage;
import com.tt.rep.service.InbillsService;

@Controller
@RequestMapping("/inbills")
public class InbillsController {
	
	@Autowired
	private InbillsService inbillsService;
	
	@RequestMapping("/doInbillsListUI")
	public String doInbillsListUI() {
		return "rep/inbills_list";
	}
	
	/**分页查询*/
	@RequestMapping("/findInbillsByPage")
	@ResponseBody
	public JsonResult findInbillsByPage(Integer pageCurrent) {
		PageObject<Inbills> inbillsByPage = inbillsService.findInbillsByPage(pageCurrent);
		return new JsonResult(inbillsByPage);
	}
	
	/**删除司机信息*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody 
	public JsonResult doDeleteObject(Integer id) {
		inbillsService.delInbills(id); 
		return new JsonResult("delete ok"); 
	}
	
	
}
