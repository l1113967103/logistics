package com.tt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.mapper.UserMapper;
import com.tt.pojo.User;
import com.tt.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
     private UserMapper userMapper;
   //进行数据校验
	@Override
	public boolean checkUser(String param, Integer type) {
	String column = (type==1)?"username":((type==2)?"phone":"email");
	QueryWrapper<User> qw = new QueryWrapper<>();
	qw.eq(column, param);
	int count = userMapper.selectCount(qw);		
	return count==0?false:true;
	
	}
}
