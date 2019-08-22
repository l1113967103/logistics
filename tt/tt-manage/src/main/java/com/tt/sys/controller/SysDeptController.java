package com.tt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tt.sys.service.SysDeptService;
import com.tt.vo.JsonResult;

@Controller
@RequestMapping("/dept")
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping("/doDeptListUI")
	public String doDeptListUI() {
		return "sys/dept_list";
	}
	
//	@RequestMapping("/doFindObjects")
//	public 
	
}
