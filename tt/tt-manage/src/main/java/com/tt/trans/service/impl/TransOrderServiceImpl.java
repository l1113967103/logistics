package com.tt.trans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.bus.service.OrderService;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Driver;
import com.tt.pojo.Outbills;
import com.tt.pojo.TransOrder;
import com.tt.pojo.Vehicle;
import com.tt.trans.mapper.TransOrderMapper;
import com.tt.trans.service.TransOrderService;
@Service
public class TransOrderServiceImpl implements TransOrderService{

	@Autowired
	private TransOrderMapper transOrderMapper;
	
	private OrderService orderService;

	/**分页信息查询*/
	@Override
	public PageObject<TransOrder> findPageObjects(String username, Integer pageCurrent) {
		QueryWrapper<TransOrder> queryWrapper = new QueryWrapper<>();
		//查询汽车总数量
		Integer count = 0;
		/**当前页要呈现的记录*/
		List<TransOrder> records = null;
		if(null==username||"".equals(username)) {
			count = transOrderMapper.selectCount(null);//查询所有运输单数量
			if(count==0)
				throw new RuntimeException("没有运输单信息");
			records = transOrderMapper.selectList(null);
		}else {
			queryWrapper.like("out_place", username).or().like("dest_place", username);
			count = transOrderMapper.selectCount(queryWrapper);//根据订单号查询所有运输单数量
			records = transOrderMapper.selectList(queryWrapper);
		}
		Integer pageSize=5;
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}

	public PageObject<TransOrder> findPageObjects(Integer transOrderId, Integer pageCurrent) {
		QueryWrapper<TransOrder> queryWrapper = new QueryWrapper<>();
		//查询汽车总数量
		Integer count = 0;
		/**当前页要呈现的记录*/
		List<TransOrder> records = null;
		if(null==transOrderId) {
			count = transOrderMapper.selectCount(null);//查询所有运输单数量
			if(count==0)
				throw new RuntimeException("没有运输单信息");
			records = transOrderMapper.selectList(null);
		}else {
			queryWrapper.eq("trans_order_id", transOrderId);
			count = transOrderMapper.selectCount(queryWrapper);//根据订单号查询所有运输单数量
			records = transOrderMapper.selectList(queryWrapper);
		}
		Integer pageSize=5;
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}

	//生成订单
	@Override
	public int createTransOrder(Outbills Outbills, Vehicle vehicle, Driver driver) {
		String destPlace = orderService.findOrder(Outbills.getOrderDescId()).getReceiverAddr();
		TransOrder transOrder = new TransOrder(null, Outbills.getOrderDescId(), driver.getId(), vehicle.getId(), Outbills.getOutputPlace(), destPlace, 1);
		int row = transOrderMapper.insert(transOrder);
		if(row==0)
			throw new RuntimeException("生成运输单失败");
		return row;
	}
}
