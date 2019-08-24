package com.tt.trans.service;

import com.tt.pojo.Vehicle;
import com.tt.sys.service.PageService;

public interface VehicleService extends PageService<Vehicle>{

	/**删除车辆信息*/
	int deleteVehicle(Integer... ids);
	/**修改车辆信息*/
	int updateVehicle(Vehicle vehicle);
	/**新增车辆信息*/
	int addVehicle(Vehicle vehicle);
}
