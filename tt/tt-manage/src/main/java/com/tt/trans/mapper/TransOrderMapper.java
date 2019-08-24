package com.tt.trans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.common.vo.PageObject;
import com.tt.pojo.TransOrder;

public interface TransOrderMapper extends BaseMapper<TransOrder>{

	PageObject<TransOrder> findPageObjects(Integer transOrderId, Integer pageCurrent);
}
