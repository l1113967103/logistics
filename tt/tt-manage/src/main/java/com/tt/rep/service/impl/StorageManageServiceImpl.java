package com.tt.rep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.common.vo.PageObject;
import com.tt.pojo.StorageManage;
import com.tt.rep.mapper.StorageManageMapper;
import com.tt.rep.service.StorageManageService;
@Service
public class StorageManageServiceImpl implements StorageManageService{

	@Autowired
	private StorageManageMapper storageManageMapper;

	@Override
	public PageObject<StorageManage> findStorageManageByPage(Integer pageCurrent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delOutbills(Integer... ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStorageManage(StorageManage storageManage) {
		// TODO Auto-generated method stub
		return 0;
	}
}
