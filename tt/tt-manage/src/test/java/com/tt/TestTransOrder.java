package com.tt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tt.pojo.Driver;
import com.tt.pojo.Outbills;
import com.tt.pojo.Vehicle;
import com.tt.rep.mapper.OutbillsMapper;
import com.tt.trans.mapper.DriverMapper;
import com.tt.trans.mapper.VehicleMapper;
import com.tt.trans.service.TransOrderService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTransOrder {

	@Autowired
	private TransOrderService transOrderService;
	@Autowired
	private OutbillsMapper outbillsMapper;
	@Autowired
	private VehicleMapper vehicleMapper;
	@Autowired
	private DriverMapper driverMapper;
	@Test//创建时间的设置----------------------------------------------------------
	public void createTransOrder() {
		Outbills outbills = outbillsMapper.selectById(1);
		Vehicle vehicle = vehicleMapper.selectById(1);
		Driver driver = driverMapper.selectById(1);
		int row = transOrderService.createTransOrder(outbills, vehicle, driver);
		System.out.println(row);
	}
}
