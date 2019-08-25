package com.tt.service;

import com.tt.pojo.User;

public interface DubboUserService {
	/**用户注册*/
	void saveUser(User user);
	/**用户登录*/
	String doLogin(User user);

//	void updateUser(User user);用户修改密码
}
