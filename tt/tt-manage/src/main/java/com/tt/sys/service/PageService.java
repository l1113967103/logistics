package com.tt.sys.service;

import com.tt.common.vo.PageObject;

public interface PageService<T> {
	/**根据名称分页查询,名称为null时查询全部*/
	PageObject<T> findPageObjects(String username,Integer pageCurrent);
	/**删除*/
//	int delObjects(Integer... ids);
	/**修改*/
//	int updateObjects(Object object);
}
