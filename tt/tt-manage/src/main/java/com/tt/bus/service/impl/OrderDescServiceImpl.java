package com.tt.bus.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.bus.mapper.OrderDescMapper;
import com.tt.bus.service.OrderDescService;
import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.OrderDesc;
@Service
public class OrderDescServiceImpl implements OrderDescService{

	@Autowired
	private OrderDescMapper orderDescMapper;

	@Override
	public PageObject<OrderDesc> findOrderDescByPage(Integer pageCurrent) {
		Integer pageSize=5;//每页5条数据
		Integer startIndex = (pageCurrent-1)*pageSize;
		Integer count = orderDescMapper.selectCount(null);//查询所有商品数量
		if(count==0)
			throw new ServiceException("没有商品信息");
		List<OrderDesc> records = orderDescMapper.findOrderDescByPage(startIndex, pageSize);
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}

	@Override
	public int delOrderDesc(Integer orderDescId) {
		//1.参数校验
		if(orderDescId==null)
			throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows=orderDescMapper.deleteById(orderDescId);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	//为运输单
	@Override
	public List<OrderDesc> findAllOrderDesc() {
		List<OrderDesc> orderDescList = orderDescMapper.selectList(null);
		return orderDescList;
	}

}
