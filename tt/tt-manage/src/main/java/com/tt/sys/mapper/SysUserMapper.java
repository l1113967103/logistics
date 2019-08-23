package com.tt.sys.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tt.pojo.SysEmp;
import com.tt.sys.vo.SysUserDeptVo;
public interface SysUserMapper {
	/**
	 * 基于用户id修改用户的密码
	 * @param password 新的密码
	 * @param salt 密码加密时使用的盐值
	 * @param id 用户id
	 * @return
	 */
	int updatePassword(
			@Param("password")String password,
			@Param("salt")String salt,
			@Param("id")Integer id);
	/**
	 * 基于用户名查找用户信息
	 * @param username
	 * @return
	 */
	SysEmp findUserByUserName(String username);
	/**
	 * 基于id查询用户对应的相关信息
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	
	int updateObject(SysEmp entity);
	
	int insertObject(SysEmp entity);
	
	/**
	 * 禁用或启用用户信息
	 * @param id
	 * @param valid 状态值
	 * @param modifiedUser 修改用户
	 * @return
	 */
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	
	int getRowCount(@Param("username")String username);
	List<SysUserDeptVo> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
}
