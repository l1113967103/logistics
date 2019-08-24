package com.tt.trans.service;

public interface ObjectService<T> {
	/**分页查询*/
	ObjectService<T> findPageObjects(String username,Integer pageCurrent,Class<?> clazz);
	/**删除信息*/
	int delObject(Integer id);
	/**新增信息*/
	int addObject(T t);
	/**修改信息*/
	int updateObject(T t);
}
