package com.tt.sys.service;

import com.tt.pojo.SysLog;
import com.tt.vo.PageObject;

public interface SysLogService {

	/**分页查询日志信息*/
	PageObject<SysLog> findPageObjects(String username, Integer pageCurrent);
	/**删除日志信息*/
	int deleteObjects(Integer... ids);

}
