package com.tt.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.sys.mapper.SysDeptMapper;
import com.tt.sys.service.SysDeptService;
@Service
public class SysDeptServiceImpl implements SysDeptService{

	@Autowired
	private SysDeptMapper sysDeptMapper;
}
