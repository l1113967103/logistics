package com.tt.service;

import com.tt.pojo.User;

public interface DubboUserService {
	
	void saveUser(User user);
	
	void updateUser(User user);

	String doLogin(User user);

	

}
