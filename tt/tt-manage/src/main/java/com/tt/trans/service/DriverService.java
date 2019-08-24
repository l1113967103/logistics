package com.tt.trans.service;

import com.tt.pojo.Driver;
import com.tt.sys.service.PageService;

public interface DriverService extends PageService<Driver>{

	/**删除司机信息*/
	int delDriver(Integer... ids);
	/**新增司机信息*/
	int addDriver(Driver driver);
	/**修改司机信息*/
	int updateDriver(Driver driver);
	/***/
}
