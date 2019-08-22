package com.tt.sys.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tt.common.vo.PageObject;
import com.tt.exception.ServiceException;
import com.tt.pojo.SysEmp;
import com.tt.sys.mapper.SysUserMapper;
import com.tt.sys.mapper.SysUserRoleMapper;
import com.tt.sys.service.SysUserService;
import com.tt.sys.vo.SysUserDeptVo;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserMapper sysUserDao;
	@Autowired
	private SysUserRoleMapper sysUserRoleDao;
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("参数不合法");
		//2.依据条件获取总记录数
		int rowCount = sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.依据条件获取当前页数据
		int pageSize = 3;
		int startIndex = (pageCurrent-1)*pageSize;
		List<SysUserDeptVo> records = sysUserDao.findPageObjects(username, startIndex, pageSize);
		//4.封装数据
		return PageUtil.newInstance(pageCurrent, rowCount, pageSize, records);
	}
	@RequiresPermissions("sys:user:valid")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		//1.合法性验证
		if(id==null||id<=0)
			throw new ServiceException("参数不合法,id="+id);
		if(valid!=1&&valid!=0)
			throw new ServiceException("参数不合法,valie="+valid);
		if(StringUtils.isEmpty(modifiedUser))
			throw new ServiceException("修改用户不能为空");
		//2.执行禁用或启用操作
		int rows=0;
		try{
			rows=sysUserDao.validById(id, valid, modifiedUser);
		}catch(Throwable e){
			e.printStackTrace();
			//报警,给维护人员发短信
			throw new ServiceException("底层正在维护");
		}
		//3.判定结果,并返回
		if(rows==0)
			throw new ServiceException("此记录可能已经不存在");
		return rows;
	}
	@Override
	public int saveObject(SysEmp entity, Integer[] roleIds) {
		//1.验证数据合法性
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new ServiceException("密码不能为空");
		if(roleIds==null || roleIds.length==0)
			throw new ServiceException("至少要为用户分配角色");
		//2.对密码进行加密
		String salt=UUID.randomUUID().toString();
		entity.setSalt(salt);
		//加密(先了解,讲shiro时再说)
		SimpleHash sHash=new SimpleHash("MD5",entity.getPassword(), salt);
		entity.setPassword(sHash.toHex());
		//3.保存用户自身信息
		int rows=sysUserDao.insertObject(entity);
		//4.保存用户与角色关系数据
		sysUserRoleDao.insertObjects(entity.getDeptId(),roleIds);//"1,2,3,4";
		//5.返回结果
		return rows;
	}
	//修改用户密码
	@Override
	public int updatePassword(String oldPwd, String newPwd1,String newPwd2) {
		//1.检查密码的合法性
		if(oldPwd==null||oldPwd.length()==0)
			throw new ServiceException("旧密码不能为空");
		//2.检验两次密码是否相同
		if(!newPwd1.equals(newPwd2))
			throw new ServiceException("两次密码不相同");
		//3.检验新密码是否与旧密码相同
		if(!oldPwd.equals(newPwd1))
			throw new ServiceException("新密码与旧密码相同,请重新输入");
		//4.修改密码
		int row = sysUserDao.updatePassword(oldPwd, newPwd1);
		if(row==0)
			throw new ServiceException("修改密码失败");
		return row;
	}

}
