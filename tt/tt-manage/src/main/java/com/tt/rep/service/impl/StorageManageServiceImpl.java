package com.tt.rep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.rep.mapper.StorageManageMapper;
import com.tt.rep.service.StorageManageService;
@Service
public class StorageManageServiceImpl implements StorageManageService{

	@Autowired
	private StorageManageMapper storageManageMapper;
}
