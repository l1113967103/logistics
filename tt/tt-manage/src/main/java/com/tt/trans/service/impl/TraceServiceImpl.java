package com.tt.trans.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.pojo.TransOrder;
import com.tt.trans.mapper.TraceMapper;
import com.tt.trans.service.TraceService;
@Service
public class TraceServiceImpl implements TraceService{

	@Autowired
	private TraceMapper traceMapper;
	/**查询运输单编号为。。。的信息*/
	@Override
	public TransOrder findByTransOrderId(Integer transOrderId) {
		QueryWrapper<TransOrder> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("trans_order_id", transOrderId);
		TransOrder transOrder = traceMapper.selectOne(queryWrapper);
		if(transOrder==null)
			throw new RuntimeException("该运输单不存在");
		return transOrder;
	}
	//实现货物追踪
	@Override
	public int updateTransOrderById(TransOrder transOrder) {
		if(transOrder.getProcess_state()==null)
			throw new RuntimeException("请输入货物状态信息");
		TransOrder transOrder2 = traceMapper.selectById(transOrder.getId());//查看数据库原有信息
		if(transOrder2==null)
			throw new RuntimeException("该运输单不存在");
		//将数据库状态与货物状态比对是否改变
		if(transOrder2.getProcess_state().equals(transOrder.getProcess_state()))
			throw new RuntimeException("请修改货物状态");
		int row = traceMapper.updateById(transOrder);
		if(row==0)
			throw new RuntimeException("货物信息修改失败");
		return row;
	}

	
}
