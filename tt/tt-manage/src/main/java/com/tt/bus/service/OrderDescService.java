package com.tt.bus.service;

import java.util.List;

import com.tt.common.vo.PageObject;
import com.tt.pojo.OrderDesc;

public interface OrderDescService {
	/**生成入库单时分页查询所有商品信息*/
	PageObject<OrderDesc> findOrderDescByPage(Integer pageCurrent);
	/**出库时删除商品表信息*/
	int delOrderDesc(Integer orderDescId);
	/**查询全部商品信息*/
	List<OrderDesc> findAllOrderDesc();
	
}
