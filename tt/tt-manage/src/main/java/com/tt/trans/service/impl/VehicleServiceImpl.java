package com.tt.trans.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.Vehicle;
import com.tt.trans.mapper.VehicleMapper;
import com.tt.trans.service.VehicleService;
@Service
public class VehicleServiceImpl implements VehicleService{

	@Autowired
	private VehicleMapper vehicleMapper;
	/**分页信息查询*/
	@Override
	public PageObject<Vehicle> findPageObjects(String username, Integer pageCurrent) {
		QueryWrapper<Vehicle> queryWrapper = new QueryWrapper<>();
		Integer pageSize=5;//每页5条数据
		Integer startIndex = (pageCurrent-1)*pageSize;
		//查询汽车总数量
		Integer count = 0;
		/**当前页要呈现的记录*/
		List<Vehicle> records = null;
		if(null==username||"".equals(username)) {
			count = vehicleMapper.selectCount(null);//查询所有汽车数量
			if(count==0)
				throw new ServiceException("没有车辆信息");
			records = vehicleMapper.selectVehicleByPage(startIndex, pageSize);
		}else {
			queryWrapper.like("name", username);
			count = vehicleMapper.selectCount(queryWrapper);//根据汽车名称查询所有汽车数量
			if(count==0)
				throw new ServiceException("没有车辆信息");
			records = vehicleMapper.selectVehicleByName(username,startIndex, pageSize);
		}
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	} 

	/**删除车辆信息*/
	@Override
	public int deleteVehicle(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows=vehicleMapper.deleteBatchIds(Arrays.asList(ids));
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	//修改车辆信息
	@Override
	public int updateVehicle(Vehicle vehicle) {
		if(null==vehicle||"".equals(vehicle.getName()))
			throw new ServiceException("请填写修改车辆信息");
		int rows = vehicleMapper.updateById(vehicle);
		if(rows==0)
			throw new ServiceException("修改车辆信息失败");
		return rows;
	}

	//新增车辆信息
	@Override
	public int addVehicle(Vehicle vehicle) {
		if(vehicle==null||"".equals(vehicle.getName()))
			throw new ServiceException("请填写新增车辆信息");
		int rows = vehicleMapper.insert(vehicle);
		if(rows==0)
			throw new ServiceException("添加车辆信息失败");
		return rows;
	}

}
