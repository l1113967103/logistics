package com.tt.sys.service;

import java.util.List;
import java.util.Map;

import com.tt.common.vo.Node;
import com.tt.pojo.SysMenu;

public interface SysMenuService {
	/**查询所有菜单信息*/
	List<Map<String,Object>> findObjects();
	/**删除菜单信息*/
	int deleteObject(Integer id);
	/**查询id，name，narentId生成ztree*/
	public List<Node> findZtreeMenuNodes();
	/**新增菜单信息*/
	int saveObject(SysMenu entity);
	/**更新菜单信息*/
	int updateObject(SysMenu entity);
	
}
