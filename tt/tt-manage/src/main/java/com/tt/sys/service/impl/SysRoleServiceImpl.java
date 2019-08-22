package com.tt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tt.common.vo.CheckBox;
import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.SysRole;
import com.tt.sys.mapper.SysRoleMapper;
import com.tt.sys.mapper.SysRoleMenuMapper;
import com.tt.sys.mapper.SysUserRoleMapper;
import com.tt.sys.service.SysRoleService;
import com.tt.sys.vo.SysRoleMenuVo;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleMapper sysRoleDao;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuDao;
	@Autowired
	private SysUserRoleMapper sysUserRoleDao;
	@Override
	public PageObject<SysRole> findObjects(String username, Integer pageCurrent) {
		//1.判定pageCurrent参数合法性
		if(pageCurrent==null||pageCurrent<1) 
			throw new IllegalArgumentException("当前页码值不正确");
		//2.查询日志总记录数,并进行判定
		int rowCount=sysRoleDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("没有找到对应记录");
		//3.查询当前页日志记录
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> records=
				sysRoleDao.findPageObjects(username,
						startIndex, pageSize);
		//4.对查询结果进行封装并返回
		return PageUtil.newInstance(pageCurrent, rowCount, pageSize, records);
	}
	@Override
	public int deleteObject(Integer id) {
		//1.验证参数的合法性
		if(id==null||id<1)
			throw new ServiceException("id的值不正确,id="+id);
		//2.执行dao操作
		int rows=sysRoleDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("数据可能已经不存在");
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		//3.返回结果
		return rows;
	}
	@Transactional
	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		//1.合法性验证
		if(entity==null)
			throw new ServiceException("保存数据不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
			throw new ServiceException("必须为角色赋予权限");
		//2.保存数据
		int rows=sysRoleDao.insertObject(entity);
//		if(rows>0)
//			throw new ServiceException("测试错误");
		sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
		//3.返回结果
		return rows;
	}
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		//1.合法性验证
		if(id==null||id<=0)
			throw new ServiceException("id的值不合法");
		//2.执行查询
		SysRoleMenuVo result=sysRoleDao.findObjectById(id);
		//3.验证结果并返回
		if(result==null)
			throw new ServiceException("此记录已经不存在");
		return result;
	}
	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		//1.合法性验证
		if(entity==null)
			throw new ServiceException("更新的对象不能为空");
		if(entity.getId()==null)
			throw new ServiceException("id的值不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
			throw new ServiceException("必须为角色指定一个权限");
		//2.更新数据
		int rows=sysRoleDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("对象可能已经不存在");
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
		//3.返回结果
		return rows;
	}
	//查取id，name用于user
	@Override
	public List<CheckBox> findObjects() {
		return sysRoleDao.findObjects();
	}
}
