package com.tt.rep.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tt.bus.mapper.OrderDescMapper;
import com.tt.bus.service.OrderDescService;
import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.Inbills;
import com.tt.pojo.Order;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Storage;
import com.tt.pojo.StorageManage;
import com.tt.rep.mapper.InbillsMapper;
import com.tt.rep.mapper.StorageManageMapper;
import com.tt.rep.mapper.StorageMapper;
import com.tt.rep.service.InbillsService;
import com.tt.rep.service.StorageService;
@Service
public class InbillsServiceImpl implements InbillsService{

	@Autowired
	private InbillsMapper inbillsMapper;
	@Autowired
	private StorageManageMapper storageManageMapper;
	//查询仓库
	@Autowired
	private StorageService storageService;
	@Autowired
	private StorageMapper storageMapper;
	//提交商品修改入库状态
	@Autowired
	private OrderDescMapper orderDescMapper;
	//分页查询修改商品入库状态后商品信息
	@Autowired
	private OrderDescService orderDescService;

	private Order order = null;
	/**在页面展示商品信息和库存信息*/
//	@Override
//	@Transactional
//	public Map<String,Object> showRepertory() {
//		Map<String,Object> map = new HashMap<>();
//		//获取threadLocal中的order,通过order获取orderDesc
//		Order order = getOrder();
//		OrderDesc orderDesc = orderDescMapper.findOrderIdByOrderDesc(order.getId());
//		orderDesc.setStatus(1).setModifiedTime(new Date());//表示商品已入库
//		orderDescMapper.updateById(orderDesc);//改变商品入库信息状态
//		//分页查询商品信息
//		PageObject<OrderDesc> orderDescByPage = orderDescService.findOrderDescByPage(1);//从第一开始
//		map.put("orderDesc", orderDescByPage);
//		PageObject<Storage> storageByPage = storageService.findStorageByPage(null, 1);
//		map.put("storage", storageByPage);
//		return map;
//	}
	/**创建入库单*/
	@Override
	@Transactional
	public int createInbills(Integer storageId, String inputPlace,Integer... orderDescIds) {
		int row = 0;
		try {
			List<OrderDesc> orderDescList = orderDescMapper.selectBatchIds(Arrays.asList(orderDescIds));
			if(orderDescList==null||orderDescList.size()==0)
				throw new ServiceException("请选择商品信息");
			Storage storage = storageMapper.selectById(storageId);
			if(storage==null)
				throw new ServiceException("该仓库不存在");
			//生成入库单
			Inbills inbills = new Inbills(null,storage.getId(), inputPlace, null, null, null, new Date());
			for (OrderDesc orderDesc : orderDescList) {
				inbills.setOrderDescId(orderDesc.getId()).setOrderDescKind(orderDesc.getKind()).setOrderDescId(orderDesc.getNum())
				.setCreatedTime(new Date()).setModifiedTime(inbills.getCreatedTime());
				row = inbillsMapper.insert(inbills);
				if(row==0)
					throw new RuntimeException("形成入库单失败");
			}
			//商品开始入库
			StorageManage storageManage = new StorageManage(null, storage.getId(), null, null, new Date(), new Date());
			for (OrderDesc orderDesc2 : orderDescList) {
				storageManage.setOrderDescKind(orderDesc2.getKind()).setOrderDescNum(orderDesc2.getNum())
				.setCreatedTime(new Date()).setModifiedTime(storageManage.getCreatedTime());
				int row1 = storageManageMapper.insert(storageManage);
				if(row1==0)
					throw new RuntimeException("入库失败");
				orderDesc2.setStatus(1).setStorageManageId(storageManage.getId()).setStorageId(storage.getId());//将商品存放仓库的id和库存id添加到商品表中
				int rowOrderDesc = orderDescMapper.updateById(orderDesc2);
				if(rowOrderDesc==0)
					throw new ServiceException("商品入库失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	//分页查询，查询全部信息
	@Override
	public PageObject<Inbills> findInbillsByPage(Integer pageCurrent) {
		Integer pageSize=5;//每页5条数据
		Integer startIndex = (pageCurrent-1)*pageSize;
		Integer count = inbillsMapper.selectCount(null);//查询所有入库单数量
		if(count==0)
			throw new ServiceException("没有入库单信息");
		List<Inbills> records = inbillsMapper.selectInbillsByPage(startIndex, pageSize);
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}
	//删除入库单
	@Override
	public int delInbills(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows=inbillsMapper.deleteBatchIds(Arrays.asList(ids));
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	//修改入库单
	@Override
	public int updateInbills(Inbills inbills) {
		if(null==inbills||"".equals(inbills.getInputPlace()))
			throw new ServiceException("请填写修改入库单信息");
		int rows = inbillsMapper.updateById(inbills);
		if(rows==0)
			throw new ServiceException("修改入库单信息失败");
		return rows;
	}
	@Override
	public void setOrder(Order order) {
		this.order = order;

	}
	@Override
	public Order getOrder() {
		return order;
	}

}
