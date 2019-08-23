package com.tt.sys.service.impl;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.SysLog;
import com.tt.sys.mapper.SysLogMapper;
import com.tt.sys.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogMapper sysLogDao;
	
	@RequiresPermissions("sys:log:delete")
	@Override
	public int deleteObjects(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0)
		throw new IllegalArgumentException("参数无效");
		//2.执行删除操作
		int rows=sysLogDao.deleteObjects(ids);
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	
	@Override
	public PageObject<SysLog> findPageObjects(
		String username, Integer pageCurrent) {
		//1.判定pageCurrent参数合法性
		if(pageCurrent==null||pageCurrent<1) 
		throw new IllegalArgumentException("当前页码值不正确");
		//2.查询日志总记录数,并进行判定
		int rowCount=sysLogDao.getRowCount(username);
		if(rowCount==0)
		throw new ServiceException("没有找到对应记录");
		//3.查询当前页日志记录
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysLog> records=
		sysLogDao.findPageObjects(username,
				startIndex, pageSize);
		//4.对查询结果进行封装并返回
		PageObject<SysLog> po=new PageObject<>();
		po.setRowCount(rowCount);
		po.setRecords(records);
		po.setPageCurrent(pageCurrent);
		po.setPageSize(pageSize);
		/*int pageCount=rowCount/pageSize;
		if(rowCount%pageSize!=0) {
			pageCount++;
		}*/
		int pageCount=(rowCount-1)/pageSize+1;
		po.setPageCount(pageCount);
		return po;
	}

}








