package com.tt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tt.common.vo.Node;
import com.tt.pojo.SysMenu;

public interface SysMenuMapper {
	/**
	 * 	判定次才到是否有子菜单
	 * @param id
	 * @return
	 */
	int getChildCount(Integer id);
	/**
	 * 	根据id删除菜单
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 	查询所有菜单信息以及上级菜单的菜单名称
	 * @return
	 */
	List<Map<String,Object>> findObjects();
	/**
	 * 	基于请求获取数据库对应的菜单表中的所有菜单信息(id,name,parentId)
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	/**
	 * 	添加插入数据的方法，用于将菜单信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysMenu entity);
	/**
	 * 	修改菜单
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	/**
	 * 	基于菜单id查找权限标识信息
	 * @param id
	 * @return
	 */
	List<String> findPermissions(@Param("menuIds")Integer[] menuIds);
}
