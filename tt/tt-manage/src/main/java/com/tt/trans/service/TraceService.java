package com.tt.trans.service;

import com.tt.pojo.TransOrder;

public interface TraceService {

	/**根据运输单号查询状态信息*/
	TransOrder findByTransOrderId(Integer transOrderId);
	
	/**修改运输单路途状态*/
	int updateTransOrderById(TransOrder transOrder);
	
}
