package com.tt.rep.service;

import java.util.Map;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Inbills;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Storage;

public interface InbillsService{

	/**进入页面时，返回商品信息和仓库信息*/
	Map<String,Object> showRepertory();
	/**生成入库单*/
	int createInbills(OrderDesc orderDesc,String inputPlace,Storage storage);
	//查询全部信息,分页查询
	PageObject<Inbills> findInbillsByPage(Integer pageCurrent);
	/**删除入库单*/
	int delInbills(Integer... ids);
	/**修改入库单*/
	int updateInbills(Inbills inbills);
	
	/**分页查询*/
//	PageObject<Inbills> findAllOrder(Inbills inbills, Integer inbillsId,Integer pageCurrent);
}
