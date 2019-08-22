package com.tt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.sys.service.SysMenuService;
import com.tt.vo.JsonResult;

@Controller
@RequestMapping("/menu")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping("/doMenuListUI")
	public String doMenuListUI() {
		return "sys/menu_list";
	}
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		return JsonResult.ok(sysMenuService.findObjects());
	}
}
