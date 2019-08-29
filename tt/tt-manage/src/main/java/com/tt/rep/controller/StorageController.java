package com.tt.rep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;
import com.tt.pojo.Storage;
import com.tt.rep.service.StorageService;

@Controller
@RequestMapping("/storage")
public class StorageController {

	@Autowired
	private StorageService storageService;
	
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(){
		List<Storage> storage = storageService.findAllStorage();
		return new JsonResult(storage);
	}
}
