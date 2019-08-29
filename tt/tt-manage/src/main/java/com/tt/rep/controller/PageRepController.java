package com.tt.rep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tt.bus.service.OrderDescService;
import com.tt.common.vo.JsonResult;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Storage;
import com.tt.rep.service.StorageService;

@Controller
@RequestMapping("/rep")
public class PageRepController {

	@Autowired
	private StorageService storageService;
	@Autowired
	private OrderDescService orderDescService;

	@RequestMapping("/doRepListUI")
	public String doRepListUI(Model model) {
		List<Storage> storageList = storageService.findAllStorage();
		model.addAttribute("storageList", storageList);
		return "rep/ware_list";
	}
	
	@RequestMapping("/doSelectObjects")
	public JsonResult doSelectObjects(Integer id,String place,Integer... ids) {
		
		return null;
	}
	
	
}
