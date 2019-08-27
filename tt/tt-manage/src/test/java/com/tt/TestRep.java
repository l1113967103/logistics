package com.tt;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tt.bus.mapper.OrderDescMapper;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Inbills;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Outbills;
import com.tt.pojo.Storage;
import com.tt.pojo.StorageManage;
import com.tt.rep.mapper.InbillsMapper;
import com.tt.rep.mapper.OutbillsMapper;
import com.tt.rep.mapper.StorageManageMapper;
import com.tt.rep.mapper.StorageMapper;
import com.tt.rep.service.InbillsService;
import com.tt.rep.service.OutbillsService;
import com.tt.rep.service.StorageManageService;
import com.tt.rep.service.StorageService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRep {
	@Autowired
	private InbillsService inbillsService;
	@Autowired
	private InbillsMapper inbillsMapper;
	@Autowired
	private OrderDescMapper orderDescMapper;
	@Autowired
	private StorageMapper storageMapper;
	//-------------------------------inbills测试(第1,2个)++++++++++++++++++++++++++++++
	/**进入页面时，返回商品信息和仓库信息*/
	@Test//*************************************
	public void showRepertory() {
		Map<String, Object> map = inbillsService.showRepertory();
		System.out.println(map);
	}
	/**生成入库单*/
	@Test//***************************++++++++++++++++++++++++++++++++++++++++++
	public void createInbills() {
		OrderDesc orderDesc = orderDescMapper.selectById(1);
		Storage storage = storageMapper.selectById(22);
		int row = inbillsService.createInbills(orderDesc, "宁夏", storage);
		System.out.println(row);
	}
	//查询全部信息,分页查询
	@Test
	public void findInbillsByPage1() {
		PageObject<Inbills> pageObject = inbillsService.findInbillsByPage(1);
		System.out.println(pageObject);
	}
	/**删除入库单*/
	@Test
	public void delInbills() {
		int rows = inbillsService.delInbills(2201,2202);
		System.out.println(rows);
	}
	/**修改入库单*/
	@Test
	public void updateInbills() {
		Inbills inbills = inbillsMapper.selectById(2203).setStorageId(3);
		int row = inbillsService.updateInbills(inbills);
		System.out.println(row);
	}
	//-------------------------------outbills测试(第一个失败)+++++++++++++++++++++++++++=+
	@Autowired
	private OutbillsService outbillsService;
	@Autowired
	private OutbillsMapper outbillsMapper;
	/**生成出库单*/
	@Test//+++++++++++++++++++++++++++++++++++++++++++++
	public void createOutbills() {
		OrderDesc orderDesc = orderDescMapper.selectById(1);
		Storage storage = storageMapper.selectById(1);
		int row = outbillsService.createOutbills(orderDesc, "北京", storage);
		System.out.println(row);
	}
	//查询全部信息,分页查询
	@Test
	public void findInbillsByPage() {
		PageObject<Outbills> pageObject = outbillsService.findInbillsByPage(1);
		System.out.println(pageObject);
	}
	/**删除入库单*/
	@Test
	public void delOutbills() {
		int rows = outbillsService.delOutbills(2,3);
		System.out.println(rows);
	}
	/**修改入库单*/
	@Test
	public void updateOutbills() {
		Outbills outbills = outbillsMapper.selectById(2).setStorageId(2);
		int row = outbillsService.updateOutbills(outbills);
		System.out.println(row);
	}
	//-------------------------------StorageManage测试（完全通过）------------------------------
	@Autowired
	private StorageManageService storageManageService;
	@Autowired
	private StorageManageMapper storageManageMapper;
	/**库存分页查询全部信息*/
	@Test
	public void findStorageManageByPage() {
		PageObject<StorageManage> pageObject = storageManageService.findStorageManageByPage(1);
		System.out.println(pageObject);
	}
	/**当出库的时候删除库存*/
	@Test
	public void delStorageManage() {
		//当输入的id全部不存在时抛出异常
		int row = storageManageService.delStorageManage(40);
		System.out.println(row);
	}
	/**修改库存*/
	@Test
	public void updateStorageManage() {
		StorageManage storageManage = storageManageMapper.selectById(23).setStorageId(23);
		int row = storageManageService.updateStorageManage(storageManage);
		System.out.println(row);
	}
	//-------------------------------Storage测试（完全通过）-----------------------------------
	@Autowired
	private StorageService storageService;
	/**分页查询仓库信息,根据仓库名称分页*/
	@Test
	public void findStorageByPage() {
		PageObject<Storage> pageObject = storageService.findStorageByPage(null, 1);
		System.out.println(pageObject);
	}
	/**新增仓库信息*/
	@Test///////////////////////////////////试一下有id成功
	public void addStorage() {
		Storage storage = storageMapper.selectById(1).setName("张三");
		int row = storageService.addStorage(storage);
		System.out.println(row);
	}
	/**修改仓库信息*/
	@Test
	public void updateStorage() {
		Storage storage = storageMapper.selectById(1).setName("小飞侠");
		int row = storageService.updateStorage(storage);
		System.out.println(row);
	}
	/**删除仓库信息*/
	@Test
	public void delStorage() {
		//给的多个id，数据库中有那个就删除哪个，没有的不删除，不报错
		int rows = storageService.delStorage(1,2,3);
		System.out.println(rows);
	}
}
