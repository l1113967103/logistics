package com.tt.trans.service;

import com.tt.pojo.Driver;
import com.tt.pojo.Outbills;
import com.tt.pojo.TransOrder;
import com.tt.pojo.Vehicle;
import com.tt.sys.service.PageService;

public interface TransOrderService extends PageService<TransOrder>{

	/**生成运输单*/
	int createTransOrder(Outbills Outbills,Vehicle vehicle,Driver driver);
}
