package com.tt.sys.service;

import java.util.List;
import java.util.Map;

import com.tt.common.vo.Node;
import com.tt.pojo.SysMenu;

public interface SysMenuService {

	/**
	 * 保存菜单信息到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	/**
	 * 保存菜单信息到数据库
	 * @param entity
	 * @return
	 */
	int saveObject(SysMenu entity);
	
	List<Node> findZtreeMenuNodes();
	List<Map<String,Object>> findObjects();
	/**
	 * 基于菜单id删除菜单元素
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
}
