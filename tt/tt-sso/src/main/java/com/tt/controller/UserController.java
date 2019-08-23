package com.tt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tt.common.vo.SysResult;
import com.tt.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
	private UserService userService;
    @Autowired
	private JedisCluster jedisCluster;
	
	/**
	 * 需求:
	 * 	根据用户传递的数据进行校验
	 * 返回值:
	 * 	SysResult 其中data表示boolean值	
	 * 			  true 表示数据已存在
	 * 			  false 表示数据可以使用
	 * 调用方式:
	 * 	 前台页面采用jsonp方法调用
	 * 最终返回值必须经过特殊格式封装 JSONPObject
	 */
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject checkUser(
				@PathVariable String param,
				@PathVariable Integer type,
				String callback) {
		boolean flag = userService.checkUser(param,type);
		SysResult result = SysResult.ok(flag);
		return new JSONPObject(callback,result);
	}
	//实现用户数据回显
	@RequestMapping("/query/{token}")
	public JSONPObject findUserByToken(@PathVariable String token,
			String callback) {
		String userJSON = jedisCluster.get(token);
		if(StringUtils.isEmpty(userJSON)) {
			return new JSONPObject(callback,SysResult.fail());
		}
		return new JSONPObject(callback, SysResult.ok(userJSON));
	}
}
