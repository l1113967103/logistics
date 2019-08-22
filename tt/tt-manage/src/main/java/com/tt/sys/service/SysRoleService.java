package com.tt.sys.service;

import java.util.List;

import com.tt.common.vo.CheckBox;
import com.tt.common.vo.PageObject;
import com.tt.pojo.SysRole;
import com.tt.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	/**
	 * 	查询所有角色信息
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysRole> findObjects(String name,Integer pageCurrent); 
	/**
	 * 	删除角色信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 	添加角色信息
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int saveObject(SysRole entity,Integer[] menuIds);
	/**
	 * 	基于角色id查询角色及关联的菜单信息
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id) ;
	/**
	 * 	修改自身信息
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int updateObject(SysRole entity,Integer[] menuIds);
	/**
	 * 	查询id，name用于user
	 * @return
	 */
	List<CheckBox> findObjects();
}
