package com.tt.trans.service;

import java.util.List;

import com.tt.common.vo.PageObject;
import com.tt.pojo.Driver;
import com.tt.pojo.Outbills;
import com.tt.pojo.TransOrder;
import com.tt.pojo.Vehicle;
import com.tt.sys.service.PageService;
import com.tt.sys.vo.ShowTransOrder;

public interface TransOrderService extends PageService<TransOrder>{

	/**生成运输单*/
	int createTransOrder(Outbills outbills,Vehicle vehicle,Driver driver);
	/**重载分页查询*/
	PageObject<TransOrder> findPageObjects(Integer transId, Integer pageCurrent);
	/**给用户展示的运输单数据*/
	List<ShowTransOrder> findShowAllTransOrder();
}
