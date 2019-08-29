package com.tt.rep.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tt.bus.mapper.OrderDescMapper;
import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Outbills;
import com.tt.pojo.Storage;
import com.tt.pojo.StorageManage;
import com.tt.rep.mapper.OutbillsMapper;
import com.tt.rep.mapper.StorageManageMapper;
import com.tt.rep.mapper.StorageMapper;
import com.tt.rep.service.OutbillsService;
@Service
public class OutbillsServiceImpl implements OutbillsService{

	@Autowired
	private OutbillsMapper outbillsMapper;
	@Autowired
	private StorageManageMapper storageManageMapper;
	//查询仓库
	@Autowired
	private StorageMapper storageMapper;
	//提交货物修改入库状态
	@Autowired
	private OrderDescMapper orderDescMapper;
	//分页查询修改货物入库状态后货物信息
//	@Autowired
//	private OrderDescService orderDescService;
	/**
	 * 仓库id,出库地place,货物ids
	 */
	@Override
	@Transactional
	public int createOutbills(Integer id, String place, Integer[] ids) {
		Storage storage = storageMapper.selectById(id);
		if(storage==null)
			throw new ServiceException("该仓库不存在");
		List<OrderDesc> orderDescList = orderDescMapper.selectBatchIds(Arrays.asList(ids));
		if(orderDescList==null||orderDescList.size()==0)
			throw new ServiceException("没有该货物");
		int row = 0;
		try {
			//生成出库单
			Outbills outbills = new Outbills(null,storage.getId(), place, null, null, null, new Date());
			outbills.setCreatedTime(new Date()).setModifiedTime(outbills.getCreatedTime());
			for (OrderDesc orderDesc : orderDescList) {
				outbills.setOrderDescId(orderDesc.getId()).setOrderDescKind(orderDesc.getKind()).setOrderDescNum(orderDesc.getNum());
				row = outbillsMapper.insert(outbills);
				if(row==0)
					throw new ServiceException("形成出库单失败");
				//出库单出库成功，修改货物状态为已出库,此时货物表中含有仓库id
				orderDesc.setStatus(2);
				int rowOrderDesc = orderDescMapper.updateById(orderDesc);
				if(rowOrderDesc==0)
					throw new ServiceException("货物出库失败");
				//货物开始出库
				if(orderDesc.getStorageId()!=storage.getId())
					throw new ServiceException("该仓库中没有该货物");
				if(orderDesc.getStorageManageId()==null)
					throw new ServiceException("该货物没有入库存");
				//查询仓库id为。。。的库存，然后将该货物类型减一
				//			通过库存id，货物种类，做货物数量的减少
				StorageManage storageManage = storageManageMapper.selectById(orderDesc.getStorageManageId());
				String orderDescKind = storageManage.getOrderDescKind();
				if(!orderDescKind.equals(orderDesc.getKind()))//通过该库存中的该货物名称和页面传来的货物名称进行比对
					throw new ServiceException("库存中没有该货物");
				Integer storageManageNum = storageManage.getOrderDescNum();
				if(storageManageNum<orderDesc.getNum())
					throw new ServiceException("出库数量异常，库存不足");
				storageManageNum -= orderDesc.getNum();//库存减少
				if(storageManageNum==0)//如果该货物数量为0，删除库存中该货物记录
					storageManageMapper.deleteById(storageManage.getId());
				storageManage.setOrderDescNum(storageManageNum);
				//修改出库后的货物数量，并存储到货物库中
				int row1 = storageManageMapper.updateById(storageManage);
				if(row1==0)
					throw new ServiceException("出库失败");
			}
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
	//查看出库单id
	@Override
	public List<Outbills> findOutbillsId() {
		List<Outbills> outbillsList = outbillsMapper.selectList(null);
		if(outbillsList==null||outbillsList.size()==0)
			throw new ServiceException("目前没有出库单");
		return outbillsList;
	}

}
