package com.tt.rep.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.Order;
import com.tt.pojo.Storage;
import com.tt.rep.mapper.StorageMapper;
import com.tt.rep.service.StorageService;
@Service
public class StorageServiceImpl implements StorageService{

	@Autowired
	private StorageMapper storageMapper;
	/**分页查询*/
	@Override
	public PageObject<Storage> findStorageByPage(Integer name, Integer pageCurrent) {
		QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
		Integer pageSize=5;//每页5条数据
		Integer startIndex = (pageCurrent-1)*pageSize;
		//查询订单总数量
		Integer count = 0;
		/**当前页要呈现的记录*/
		List<Storage> records = null;
		if(null==name||"".equals(name)) {
			count = storageMapper.selectCount(null);//查询所有订单数量
			if(count==0)
				throw new RuntimeException("没有仓库信息");
			records = storageMapper.selectStorageNameByPage(startIndex, pageSize);
		}else {
			queryWrapper.like("name", name);
			count = storageMapper.selectCount(queryWrapper);//根据订单编号查询所有订单数量
			if(count==0)
				throw new RuntimeException("没有仓库信息");
			records = storageMapper.selectStorageNameByPage(name,startIndex, pageSize);
		}
		int pageCount=(count-1)/pageSize+1;
		//		return null;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}

	@Override
	public int addStorage(Storage storage) {
		if(storage==null||"".equals(storage.getName()))
			throw new RuntimeException("请填写新增仓库信息");
		int rows = storageMapper.insert(storage);
		if(rows==0)
			throw new RuntimeException("添加仓库信息失败");
		return rows;
	}

	@Override
	public int updateStorage(Storage storage) {
		if(null==storage||"".equals(storage.getName()))
			throw new RuntimeException("请填写仓库车辆信息");
		int rows = storageMapper.updateById(storage);
		if(rows==0)
			throw new RuntimeException("修改仓库信息失败");
		return rows;
	}

	@Override
	public int delStorage(Integer... storageId) {
		//1.参数校验
		if(storageId==null||storageId.length==0)
			throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows=storageMapper.deleteBatchIds(Arrays.asList(storageId));
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}

}
