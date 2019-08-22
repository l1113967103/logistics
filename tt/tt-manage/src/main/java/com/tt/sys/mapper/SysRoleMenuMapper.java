package com.tt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuMapper {
	/**
	 * 	
	 * @param id
	 * @return
	 */
	int deleteObjectsByMenuId(Integer id);
	/**
	 * 	通过roleId 删除角色关联菜单
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
	/**
	 * 	新增角色信息关联的菜单
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int insertObjects(@Param("roleId")Integer roleId,@Param("menuIds")Integer[] menuIds);
	/**
	 * 	基于用户id查找菜单id信息
	 * @param id
	 * @return
	 */
	List<Integer> findMenuIdsByRoleIds(@Param("roleIds")Integer[] roleIds);
}
