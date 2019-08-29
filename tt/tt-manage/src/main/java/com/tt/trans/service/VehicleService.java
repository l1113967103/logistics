package com.tt.trans.service;

import java.util.List;

import com.tt.pojo.Vehicle;
import com.tt.sys.service.PageService;

public interface VehicleService extends PageService<Vehicle>{

	/**删除车辆信息*/
	int deleteVehicle(Integer... ids);
	/**修改车辆信息*/
	int updateVehicle(Vehicle vehicle);
	/**新增车辆信息*/
	int addVehicle(Vehicle vehicle);
	/**根据id查询车辆信息，修改时页面回显数据*/
	Vehicle findObjectById(Integer id);
	/**查询所有车辆信息，为运输单*/
	List<Vehicle> findAllVehicle();
	
}
