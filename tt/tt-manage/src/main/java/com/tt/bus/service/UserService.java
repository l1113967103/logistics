package com.tt.bus.service;

import java.util.List;

import com.tt.pojo.User;

public interface UserService {

	/**新增用户信息*/
//	int addUser(User user);
	/**删除用户信息*/
	int delUser(Integer userId);
	/**修改用户信息*/
//	int updateUser(User user);
	/**查询用户信息*/
	User findUser(Integer userId);
	/**查询用户信息*/
	List<User> findAllUser();
	
}
