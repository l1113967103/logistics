package com.tt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.tt.common.vo.PageObject;
import com.tt.pojo.Vehicle;
import com.tt.trans.service.VehicleService;
@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.tt.trans.mapper")
public class TestVehicle {

	@Autowired
	private VehicleService vehicleService;
	@Test
	public void addVehicle() {
		Vehicle vehicle = new Vehicle(null, 1, "小飞侠", "小汽车", 1, null);
		vehicleService.addVehicle(vehicle);
	}
	@Test
	public void delVehicle() {
		vehicleService.deleteVehicle(2);
	}
	@Test
	public void findVehicle() {
		PageObject<Vehicle> Vehicle = vehicleService.findPageObjects("", 1);
		System.out.println(Vehicle);
	}
	@Test
	public void updVehicle() {
		Vehicle vehicle = new Vehicle(8,2, "小飞侠", "小汽车", 2, null);
		vehicleService.updateVehicle(vehicle);
	}
}
