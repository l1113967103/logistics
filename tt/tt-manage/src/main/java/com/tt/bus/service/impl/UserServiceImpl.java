package com.tt.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.bus.mapper.UserMapper;
import com.tt.bus.service.UserService;
import com.tt.pojo.User;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Override
	public int delUser(Integer userId) {
		int row = 0;
		try {
			row = userMapper.deleteById(userId);
			if(row==0)
				throw new RuntimeException("删除用户失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public User findUser(Integer userId) {
		User user = userMapper.selectById(userId);
		if(user==null)
			throw new RuntimeException("没有用户信息");
		return user;
	}

	@Override
	public List<User> findAllUser() {
		List<User> userList = userMapper.selectList(null);
		if(userList==null||userList.size()==0)
			throw new RuntimeException("没有用户信息");
		return userList;
	}

}
