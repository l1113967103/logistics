package com.tt.trans.service;

import com.tt.common.vo.PageObject;
import com.tt.pojo.Driver;
import com.tt.pojo.Outbills;
import com.tt.pojo.TransOrder;
import com.tt.pojo.Vehicle;
import com.tt.sys.service.PageService;

public interface TransOrderService extends PageService<TransOrder>{

	/**生成运输单*/
	int createTransOrder(Outbills outbills,Vehicle vehicle,Driver driver);
	/**重载分页查询*/
	PageObject<TransOrder> findPageObjects(Integer transId, Integer pageCurrent);
}
