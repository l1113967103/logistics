package com.tt.trans.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.Driver;
import com.tt.trans.mapper.DriverMapper;
import com.tt.trans.service.DriverService;
@Service
public class DriverServiceImpl implements DriverService{

	@Autowired
	private DriverMapper driverMapper;
	/**分页查询司机信息*/
	@Override
	public PageObject<Driver> findPageObjects(String username, Integer pageCurrent) {
		QueryWrapper<Driver> queryWrapper = new QueryWrapper<>();
		//查询司机总数
		Integer count = 0;
		/**当前页要呈现的记录*/
		List<Driver> records = null;
		if(null==username||"".equals(username)) {
			count = driverMapper.selectCount(null);//查询所有司机人数
			if(count==0)
				throw new ServiceException("没有司机信息");
			records = driverMapper.selectList(null);
		}else {
			queryWrapper.like("name", username);
			count = driverMapper.selectCount(queryWrapper);//根据name查询司机人数
			records = driverMapper.selectList(queryWrapper);
		}
		Integer pageSize=5;
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}

	@Override
	public int delDriver(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows=driverMapper.deleteBatchIds(Arrays.asList(ids));
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	@Override
	public int addDriver(Driver driver) {
		if(null==driver||"".equals(driver.getName()))
			throw new ServiceException("请填写新增司机信息");
		driver.setStatus(0)
		.setCreatedTime(new Date())
		.setModifiedTime(driver.getCreatedTime());
		int rows = driverMapper.insert(driver);
		if(rows==0)
			throw new ServiceException("新增司机信息失败");
		return rows;
	}

	@Override
	public int updateDriver(Driver driver) {
		if(driver==null||"".equals(driver.getName()))
			throw new ServiceException("请填写修改司机信息");
		driver.setName(driver.getName()).setModifiedTime(new Date());
		int rows = driverMapper.updateById(driver);
		if(rows==0)
			throw new ServiceException("修改司机信息失败");
		return rows;
	}

	@Override
	public Driver findObjectById(Integer id) {
		Driver driver = driverMapper.selectById(id);
		if(driver==null)
			throw new ServiceException("该司机不存在，系统出现错误");
		return driver;
	}
	//查询所有司机信息，只要id，姓名，状态
	@Override
	public List<Driver> findAllDriver() {
		QueryWrapper<Driver> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("status", 0);
		List<Driver> driverList = driverMapper.selectList(queryWrapper);
		if(driverList==null||driverList.size()==0)
			throw new ServiceException("没有空闲的司机");
		return driverList;
	}

}
