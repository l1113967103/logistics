package com.tt.sys.service;

import com.tt.common.vo.PageObject;

public interface PageService<T> {
	/**根据名称分页查询*/
	PageObject<T> findPageObjects(String username,Integer pageCurrent);

}
