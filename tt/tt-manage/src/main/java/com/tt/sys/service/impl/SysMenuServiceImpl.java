package com.tt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tt.common.vo.Node;
import com.tt.exception.ServiceException;
import com.tt.pojo.SysMenu;
import com.tt.sys.mapper.SysMenuMapper;
import com.tt.sys.mapper.SysRoleMenuMapper;
import com.tt.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuMapper sysMenuDao;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuDao;
	//查询所有菜单信息
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String,Object>> list = sysMenuDao.findObjects();
		if(list==null||list.size()==0)
			throw new ServiceException("没有对应的菜单信息");
		return list;
	}
	//删除菜单信息
	@Override
	public int deleteObject(Integer id) {
		//1.验证数据的合法性
		if(id==null||id<=0)
			throw new ServiceException("请先选择");
		//2.基于id进行子元素查询
		int count = sysMenuDao.getChildCount(id);
		if(count>0)
			throw new ServiceException("请先删除子菜单");
		//3.删除菜单元素
		int rows = sysMenuDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("此菜单可能不存在");
		//4.删除角色,菜单关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id); 
		//5.返回结果
		return rows;
	}
	//查询id，name，parentId的信息
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	//新增菜单信息
	@Override
	public int saveObject(SysMenu entity) {
		//1.验证合法性
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空");
		int rows;
		//2.保存数据
		try {
			rows = sysMenuDao.insertObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存失败");
		}
		//3.返回数据
		return rows;
	}
	//更新菜单信息
	@Override
	public int updateObject(SysMenu entity) {
		//1.合法验证
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空");
		//2.更新数据
		int rows = sysMenuDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		//3.返回数据
		return rows;
	}

}
