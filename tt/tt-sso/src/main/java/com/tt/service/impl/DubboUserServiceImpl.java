package com.tt.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tt.mapper.UserMapper;
import com.tt.pojo.User;
import com.tt.service.DubboUserService;
import com.tt.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;

@Service(timeout = 3000)
public class DubboUserServiceImpl implements DubboUserService{
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisCluster jedisCluster;
	
	/**
	 * 入库操作时步骤如下
	 * 	1.密码应该加密 MD5 MD5HASH
	 * 	2.页面中暂时没有传递email 暂时使用电话代替
	 *  3.添加用户操作的时间
	 *  4.需要控制事务
	 */
	@Transactional
	@Override
	public void saveUser(User user) {
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass)
			.setEmail(user.getEmail())
			.setCreatedTime(new Date())
			.setModifiedTime(user.getCreatedTime());
		int row = userMapper.insert(user);
		if(row==0)
			throw new RuntimeException("注册失败");
	}
	
	
	/**
	 * 1.校验用户名和密码是否正确
	 * 	 如果用户名和密码不正确 则返回null
	 * 2.如果用户名和密码正确.
	 * 	 生成token秘钥/将user对象转化为json数据之后保存到redis中
	 * 3.将token数据返回.
	 */
	@Override
	public String doLogin(User user) {
//		System.out.println("user===:"+user);
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
		User userDB = userMapper.selectOne(queryWrapper);
		String token = null;
		if(userDB != null) {
			token = "TT_TICKET_"+System.currentTimeMillis()+userDB.getUsername();
			token = DigestUtils.md5DigestAsHex(token.getBytes());
			//数据比较敏感时需要做脱敏处理
			userDB.setPassword("123456");
			String userJSON = ObjectMapperUtil.toJSON(userDB);
			jedisCluster.setex(token,7*24*3600,userJSON);
		}
		return token;
	}
}
