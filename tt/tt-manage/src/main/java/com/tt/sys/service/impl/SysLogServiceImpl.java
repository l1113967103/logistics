package com.tt.sys.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.exception.ServiceException;
import com.tt.pojo.SysLog;
import com.tt.sys.mapper.SysLogMapper;
import com.tt.sys.service.SysLogService;
import com.tt.vo.PageObject;
@Service
public class SysLogServiceImpl implements SysLogService{

	@Autowired
	private SysLogMapper sysLogMapper;
	//分页查询
	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		List<SysLog> records = null;
		Integer rowCount = 0;
		QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
		if(username==null||"".equals(username)) {
			rowCount = sysLogMapper.selectCount(null);
		}else {
			queryWrapper.like("username", "%"+username+"%");
			rowCount = sysLogMapper.selectCount(queryWrapper);
		}
		Integer pageSize = 3;
		Integer pageCount = (rowCount-1)/pageSize+1;//总页数
		Integer startIndex = (pageCurrent-1)*pageSize;
		records = sysLogMapper.findPageObjects(startIndex,pageSize);
		return new PageObject<>(records, rowCount, pageCount, pageCurrent, pageSize);
	}
	@Override
	public int deleteObjects(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows = 0 ;
//		int rows = sysLogMapper.deleteObjects(ids);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		rows = sysLogMapper.deleteBatchIds(Arrays.asList(ids));
		return rows;
	}
}
