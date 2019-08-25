package com.tt.service;

import com.tt.common.vo.SysResult;

public interface DubboOrderService {

	/**返回错误订单，及其提示信息*/
	void backOrder(SysResult fail);
	
	
}
