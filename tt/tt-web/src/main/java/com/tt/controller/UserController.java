package com.tt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.tt.common.vo.JsonResult;
import com.tt.common.vo.SysResult;
import com.tt.pojo.User;
import com.tt.service.UserService;
/**
 * 	用户的登录
 * @author Administrator
 *
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	//实现用户的登录
		@RequestMapping("/doLogin")
		@ResponseBody
		public SysResult doLogin(User user,HttpServletResponse response) {
			try {
				//用户登录成功后，返回秘钥
				String token = userService.doLogin(user);
				//表示程序调用不为null
				if(!StringUtils.isEmpty(token)) {
					Cookie cookie = new Cookie("TT_TICKET",token);
					cookie.setDomain("tt.com");
					cookie.setMaxAge(7*24*3600);
					cookie.setPath("/");
					response.addCookie(cookie);
					return SysResult.ok();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SysResult.fail();
		}
}
