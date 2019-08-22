package com.tt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tt.common.vo.CheckBox;
import com.tt.pojo.SysRole;
import com.tt.sys.vo.SysRoleMenuVo;

public interface SysRoleMapper {
	/**
	 * 	查询当前页面呈现的记录
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysRole> findPageObjects(@Param("name")String name,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	/**
	 * 	查询总记录数
	 * @param name
	 * @return
	 */
	int getRowCount(String name);
	/**
	 * 	删除角色信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	/**
	 * 	添加角色信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity);
	/**
	 * 	基于基于角色id查询角色信息
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);
	/**
	 * 	修改角色自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity);
	/**
	 * 	查询角色id，name
	 * @return
	 */
	List<CheckBox> findObjects();
}
