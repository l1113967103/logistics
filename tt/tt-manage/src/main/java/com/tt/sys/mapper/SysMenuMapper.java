package com.tt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tt.common.vo.Node;
import com.tt.pojo.SysMenu;

public interface SysMenuMapper {
	/**
	 * 基于菜单id获取权限标识
	 * @param menuIds
	 * @return
	 */
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);
	/**
	 * 将内存中的SysMenu对象持久化到数据库
	 * @param entity
	 * @return 更新记录的行数
	 */
	int updateObject(SysMenu entity);
	/**
	 * 将内存中的SysMenu对象持久化到数据库
	 * @param entity
	 * @return 写入记录的行数
	 */
	int insertObject(SysMenu entity);
	
    /**
     * 查询菜单对应的树结构信息
     * @return
     */
    List<Node> findZtreeMenuNodes();
	/**
	 * 判定此菜单是否有子菜单
	 * @param id
	 * @return 子菜单的数量
	 */
	int getChildCount(Integer id);
	/**
	 * 执行删除操作
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 查询所有菜单信息以及上级菜单的菜单名称
	 * @return
	 */
	List<Map<String,Object>> findObjects();
}
