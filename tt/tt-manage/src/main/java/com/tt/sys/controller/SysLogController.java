package com.tt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.pojo.SysLog;
import com.tt.sys.service.SysLogService;
import com.tt.vo.JsonResult;
import com.tt.vo.PageObject;

@Controller
@RequestMapping("/log")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping("/doLogListUI")
	  public String doLogListUI() {
		  return "sys/log_list";
	  }
	/**分页查询用户行为日志信息*/
//	@RequestMapping("/doFindPageObjects")
//	@ResponseBody
//	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
//		PageObject<SysLog> pageObject = sysLogService.findPageObjects(username,pageCurrent);
//		return JsonResult.ok(pageObject);
//	}
	/**删除用户行为日志信息*/
//	@RequestMapping("/doDeleteObjects")
//	@ResponseBody
//	public JsonResult doDeleteObjects(Integer... ids) {
//		System.out.println(ids);
//		int rows = sysLogService.deleteObjects(ids);
//		return JsonResult.ok("delete ok,rows="+rows,null);
//	}
}
