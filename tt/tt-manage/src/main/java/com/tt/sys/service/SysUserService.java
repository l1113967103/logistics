package com.tt.sys.service;

import com.tt.pojo.SysEmp;
import com.tt.sys.vo.SysUserDeptVo;

public interface SysUserService extends PageService<SysUserDeptVo>{
	/**
	 * 	用户的禁用与启用
	 * @param id
	 * @param valid
	 * @param modifiedUser
	 * @return
	 */
	public int validById(Integer id,Integer valid,String modifiedUser);
	/**
	 * 	添加用户数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObject(SysEmp entity, Integer[] roleIds);
	/**
	 * 	修改用户密码
	 * @param oldPwd	旧密码
	 * @param newPwd	新密码
	 * @return
	 */
	int updatePassword(String oldPwd,String newPwd1,String newPwd2);
}
