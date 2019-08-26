package com.tt.rep.service;

import com.tt.common.vo.PageObject;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Outbills;
import com.tt.pojo.Storage;

public interface OutbillsService {

	/**生成出库单*/
	int createOutbills(OrderDesc orderDesc,String outPlace,Storage storage);
//	List<Outbills> transOutbills();
	//查询全部信息,分页查询
	PageObject<Outbills> findInbillsByPage(Integer pageCurrent);
	/**删除入库单*/
	int delOutbills(Integer... ids);
	/**修改入库单*/
	int updateOutbills(Outbills outbills);

}
