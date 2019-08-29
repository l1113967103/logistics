package com.tt.rep.controller;

import java.util.List;
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
import com.tt.pojo.Outbills;
import com.tt.pojo.Storage;
import com.tt.rep.service.InbillsService;
import com.tt.rep.service.OutbillsService;

@Controller
@RequestMapping("/outbills")
public class OutbillsController {
	
	@Autowired
	private OutbillsService outbillsService;
	
	@RequestMapping("/doOutbillsListUI")
	public String doInbillsListUI() {
		return "rep/outbills_list";
	}
	
	/**分页查询*/
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult findInbillsByPage(Integer pageCurrent) {
		PageObject<Outbills> inbillsByPage = outbillsService.findInbillsByPage(pageCurrent);
		return new JsonResult(inbillsByPage);
	}
	

	/**删除司机信息*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody 
	public JsonResult doDeleteObject(Integer id) {
		outbillsService.delOutbills(id);
		return new JsonResult("delete ok"); 
	}
	/**生成运输单时，需要下拉框中的订单号*/
	@RequestMapping("/doFindAllOutbills")
	@ResponseBody
	public JsonResult doFindAllOutbills() {
		List<Outbills> outbillsList = outbillsService.findOutbillsId();
		return new JsonResult(outbillsList);
	}
	
}
