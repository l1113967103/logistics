package com.tt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tt.common.vo.PageObject;
import com.tt.pojo.StorageManage;
import com.tt.rep.mapper.StorageManageMapper;
import com.tt.rep.service.StorageManageService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestStorage {
	
	@Autowired
	private StorageManageService storageManageService;
	@Autowired
	private StorageManageMapper storageManageMapper;
	
	//测试当出库的时候删除库存
	@Test
	public void testDel() {
		int row = storageManageService.delStorageManage(38);
		System.out.println(row);
	}
	//测试分页查询全部信息
	@Test
	public void findStorageManageByPage() {
		PageObject<StorageManage> manageByPage = storageManageService.findStorageManageByPage(1);
		System.out.println(manageByPage);
	}
	
	//测试修改
	@Test
	public void testUpdayte() {
		StorageManage storage = storageManageMapper.selectById(40);
		storage.setStorageId(12)
		.setOrderDescKind("lll");
		
		storageManageService.updateStorageManage(storage);
	}

}