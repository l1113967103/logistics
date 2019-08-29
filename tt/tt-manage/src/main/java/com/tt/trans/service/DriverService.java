package com.tt.trans.service;

import java.util.List;

import com.tt.pojo.Driver;
import com.tt.sys.service.PageService;

public interface DriverService extends PageService<Driver>{

	/**删除司机信息*/
	int delDriver(Integer... ids);
	/**新增司机信息*/
	int addDriver(Driver driver);
	/**修改司机信息*/
	int updateDriver(Driver driver);
	/**修改时用于数据回显*/
	Driver findObjectById(Integer id);
	/**查询所有司机信息，用于分配，生成运输单*/
	List<Driver> findAllDriver();
}
