package com.tt.rep.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.StorageManage;
import com.tt.pojo.Vehicle;
import com.tt.rep.mapper.StorageManageMapper;
import com.tt.rep.service.StorageManageService;
@Service
public class StorageManageServiceImpl implements StorageManageService{

	@Autowired
	private StorageManageMapper storageManageMapper;

	@Override
	public PageObject<StorageManage> findStorageManageByPage(Integer pageCurrent) {
		Integer pageSize=5;//每页5条数据
		Integer startIndex = (pageCurrent-1)*pageSize;
		//查询汽车总数量
		Integer count = 0;
		/**当前页要呈现的记录*/
		List<StorageManage> records = null;
		count = storageManageMapper.selectCount(null);//查询所有汽车数量
		if(count==0)
			throw new ServiceException("没有车辆信息");
		records = storageManageMapper.findStorageManageByPage(startIndex, pageSize);
		if(count==0)
			throw new ServiceException("没有车辆信息");
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}

	@Override
	public int delStorageManage(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows=storageManageMapper.deleteBatchIds(Arrays.asList(ids));
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	@Override
	public int updateStorageManage(StorageManage storageManage) {
		int rows = storageManageMapper.updateById(storageManage);
		if(rows==0)
			throw new ServiceException("修改车辆信息失败");
		return rows;
	}
}
