package com.tt.sys.service;

import com.tt.common.vo.PageObject;

public interface PageService<T> {
	public PageObject<T> findPageObjects(String username, Integer pageCurrent);
}
