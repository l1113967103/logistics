package com.tt.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.tt.pojo.SysEmp;
import com.tt.sys.vo.SysUserDeptVo;

public interface SysUserMapper extends PageMapper<SysUserDeptVo>{
	/**
	 * 	查询所有用户分页信息
	 * @param username
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
//	List<SysUserDeptVo> findPageObjects(@Param("username") String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	/**
	 * 	查询所有用户个数
	 * @param username
	 * @return
	 */
//	int getRowCount(@Param("username")String username);
	/**
	 * 	禁用和启用状态
	 * @param id
	 * @param valid
	 * @param modifiedUser
	 * @return
	 */
	int validById(@Param("id")Integer id,@Param("valid")Integer valid,@Param("modifiedUser")String modifiedUser);
	/**
	 * 	存储用户数据
	 * @param sysUser
	 * @return
	 */
	int insertObject(SysEmp entity);
	/**
	 * 	根据id查询
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	/**
	 * 	通过用户名查取用户信息
	 * @param username
	 * @return
	 */
	SysEmp findUserByUserName(String username);
	/**
	 * 	修改用户密码
	 * @param oldPwd	旧密码
	 * @param newPwd	新密码
	 * @return
	 */
	int updatePassword(String oldPwd,String newPwd);
}
