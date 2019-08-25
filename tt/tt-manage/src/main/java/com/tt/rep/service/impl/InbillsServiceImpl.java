package com.tt.rep.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tt.pojo.Inbills;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Storage;
import com.tt.pojo.StorageManage;
import com.tt.rep.mapper.InbillsMapper;
import com.tt.rep.mapper.StorageManageMapper;
import com.tt.rep.service.InbillsService;
@Service
public class InbillsServiceImpl implements InbillsService{

	@Autowired
	private InbillsMapper inbillsMapper;
	@Autowired
	private StorageManageMapper storageManageMapper;

	@Override
	@Transactional
	public int createInbills(OrderDesc orderDesc, String inputPlace,Storage storage) {
		int row = 0;
		try {
			//生成入库单
			Inbills inbills = new Inbills(null,storage.getId(), inputPlace, orderDesc.getId(), orderDesc.getKind(), orderDesc.getNum(), new Date());
			row = inbillsMapper.insert(inbills);
			if(row==0)
				throw new RuntimeException("形成入库单失败");
			//商品开始入库
			StorageManage storageManage = new StorageManage(null, storage.getId(), orderDesc.getKind(), orderDesc.getNum(), new Date(), new Date());
			int row1 = storageManageMapper.insert(storageManage);
			if(row1==0)
				throw new RuntimeException("入库失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
}
