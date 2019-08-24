package com.tt.trans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.bus.service.OrderDescService;
import com.tt.pojo.Order;
import com.tt.pojo.Outbills;
import com.tt.pojo.TransOrder;
import com.tt.rep.service.OutbillsService;
import com.tt.trans.mapper.TraceMapper;
import com.tt.trans.service.TraceService;
@Service
public class TraceServiceImpl implements TraceService{

	@Autowired
	private TraceMapper traceMapper;
	@Autowired
	private OutbillsService outbillsService;//库存部门
	@Autowired
	private OrderDescService orderDescService;//业务部门(订单操作)
	//需要order、Outbills
//	public int createTransOrder() {
//		List<Outbills> outbillsList = outbillsService.transOutbills();
//		if(outbillsList==null)
//			throw new RuntimeException("没有入库单无法生成运输单");
//		for (Outbills outbills : outbillsList) {
//			Integer orderDescId = outbills.getOrderDescId();
//			//根据商品id查询订单id，根据订单id查询订单详情
//			Order order = orderDescService.findOrderByOrderDescId(orderDescId);
//			TransOrder transOrder = new TransOrder(null, orderDescId, driverId, vehicleId, order.getSenderAddr(), order.getReceiverAddr(), status)
//			traceMapper.insert(entity);
//		}
//		return 0;
//	}

}
