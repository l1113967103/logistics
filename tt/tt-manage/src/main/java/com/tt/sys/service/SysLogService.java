package com.tt.sys.service;

import com.tt.pojo.SysLog;

public interface SysLogService extends PageService<SysLog>{
	   /**
	    * 按条件分页查询日志信息
	    * @param username 查询条件
	    * @param pageCurrent 当前页码值
	    * @return 当前页日志信息以及分页信息
	    */
	   /*PageObject<SysLog> findPageObjects(
			  String username,
			  Integer pageCurrent);*/
	   public int deleteObjects(Integer... ids);
}







