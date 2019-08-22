package com.tt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.PageObject;
import com.tt.common.vo.JsonResult;
import com.tt.pojo.SysLog;
import com.tt.sys.service.SysLogService;


@Controller
@RequestMapping("/log/")//日志模块根路径
public class SysLogController {
	  @Autowired
	  private SysLogService sysLogService;
	  @RequestMapping("doLogListUI")
	  public String doLogListUI() {
		  return "sys/log_list";
	  }
	  /**
	   * 分页查询用户行为日志信息
	   * @param username
	   * @param pageCurrent
	   * @return
	   */
	  @RequestMapping("doFindPageObjects")
	  @ResponseBody
	  public JsonResult doFindPageObjects(
			  String username,Integer pageCurrent) {
		  PageObject<SysLog> pageObject=
		  sysLogService.findPageObjects(username,
				  pageCurrent);
		  return new JsonResult(pageObject);
	  }
	  @RequestMapping("doDeleteObjects")
	  @ResponseBody
	  public JsonResult doDeleteObjects(Integer... ids) {
		  int rows = sysLogService.deleteObjects(ids);
		  return new JsonResult("delete ok,rows="+rows);
	  }
	  
}