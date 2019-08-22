package com.tt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
	/**
	 * 	删除角色关联的用户
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
	/**
	 * 	添加用户角色关系数据
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	int insertObjects(@Param("userId")Integer userId,@Param("roleIds")Integer[]roleIds);
	/**
	 * 	基于用户id查找角色id信息
	 * @param id
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer id);
	
}
