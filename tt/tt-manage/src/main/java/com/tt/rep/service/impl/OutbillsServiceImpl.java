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
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Outbills;
import com.tt.pojo.Storage;
import com.tt.pojo.StorageManage;
import com.tt.rep.mapper.OutbillsMapper;
import com.tt.rep.mapper.StorageManageMapper;
import com.tt.rep.service.OutbillsService;
import com.tt.rep.service.StorageService;
@Service
public class OutbillsServiceImpl implements OutbillsService{

	@Autowired
	private OutbillsMapper outbillsMapper;
	@Autowired
	private StorageManageMapper storageManageMapper;
	//查询仓库
	@Autowired
	private StorageService storageService;
	//提交商品修改入库状态
	@Autowired
	private OrderDescMapper orderDescMapper;
	//分页查询修改商品入库状态后商品信息
	@Autowired
	private OrderDescService orderDescService;
	@Override
	@Transactional
	public int createOutbills(OrderDesc orderDesc, String outPlace, Storage storage) {
		int row = 0;
		try {
			//生成出库单
			Outbills outbills = new Outbills(null,storage.getId(), outPlace, orderDesc.getId(), orderDesc.getKind(), orderDesc.getNum(), new Date());
			outbills.setCreatedTime(new Date()).setModifiedTime(outbills.getCreatedTime());
			row = outbillsMapper.insert(outbills);
			if(row==0)
				throw new RuntimeException("形成出库单失败");
			//出库单出库成功，修改商品状态为已出库,此时商品表中含有仓库id
			orderDesc.setStatus(2);
			int rowOrderDesc = orderDescMapper.updateById(orderDesc);
			if(rowOrderDesc==0)
				throw new ServiceException("商品出库失败");
			//商品开始出库
			if(orderDesc.getStorageId()!=storage.getId())
				throw new ServiceException("该仓库中没有该商品");
			if(orderDesc.getStorageManageId()==null)
				throw new ServiceException("该商品没有入库存");
			//查询仓库id为。。。的库存，然后将该商品类型减一
			//			通过库存id，商品种类，做商品数量的减少
			StorageManage storageManage = storageManageMapper.selectById(orderDesc.getStorageManageId());
			String orderDescKind = storageManage.getOrderDescKind();
			if(!orderDescKind.equals(orderDesc.getKind()))//通过该库存中的该商品名称和页面传来的商品名称进行比对
				throw new ServiceException("库存中没有该商品");
			Integer storageManageNum = storageManage.getOrderDescNum();
			if(storageManageNum<orderDesc.getNum())
				throw new ServiceException("出库数量异常，库存不足");
			storageManageNum -= orderDesc.getNum();//库存减少
			if(storageManageNum==0)//如果该商品数量为0，删除库存中该商品记录
				storageManageMapper.deleteById(storageManage.getId());
			storageManage.setOrderDescNum(storageManageNum);
			//修改出库后的商品数量，并存储到商品库中
			int row1 = storageManageMapper.updateById(storageManage);
			if(row1==0)
				throw new RuntimeException("出库失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public PageObject<Outbills> findInbillsByPage(Integer pageCurrent) {
		Integer pageSize=5;//每页5条数据
		Integer startIndex = (pageCurrent-1)*pageSize;
		Integer count = outbillsMapper.selectCount(null);//查询所有出库单数量
		if(count==0)
			throw new ServiceException("没有出库单信息");
		List<Outbills> records = outbillsMapper.selectOutbillsByPage(startIndex, pageSize);
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}

	@Override
	public int delOutbills(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows=outbillsMapper.deleteBatchIds(Arrays.asList(ids));
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	@Override
	public int updateOutbills(Outbills outbills) {
		if(null==outbills||"".equals(outbills.getOutputPlace()))
			throw new ServiceException("请填写修改出库单信息");
		int rows = outbillsMapper.updateById(outbills);
		if(rows==0)
			throw new ServiceException("修改出库单信息失败");
		return rows;
	}

}
