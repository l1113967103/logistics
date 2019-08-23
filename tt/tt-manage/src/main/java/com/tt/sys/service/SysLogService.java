package com.tt.sys.service;

import com.tt.pojo.SysLog;

public interface SysLogService extends PageService<SysLog>{
	
	   
	   /**
	    * 基于id删除日志信息
	    * @param ids
	    * @return
	    */
	   int deleteObjects(Integer... ids);
	  

}







