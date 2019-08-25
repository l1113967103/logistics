package com.tt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 实现用户的注册和登录页面
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.tt.common.vo.JsonResult;
import com.tt.common.vo.SysResult;
import com.tt.pojo.User;
import com.tt.service.DubboUserService;
@Controller
public class UserController {
	
	@Reference(timeout = 3000,check=false)
	private DubboUserService userService;
	
	//点击注册或登录按钮时，页面自动跳转至该页面
	@RequestMapping("/{moduleName}")
	public String module(@PathVariable String moduleName) {
		return moduleName;
	}
	//实现注册页面
	@RequestMapping("/doRegister")
	@ResponseBody
	public JsonResult saveUser(User user) {
		try {
			userService.saveUser(user);
			return new JsonResult();
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult("注册失败");
		}
	}
	//实现用户登录页面
	public JsonResult doLogin(User user,HttpServletResponse response) {
		try {
			//用户登录成功返回秘钥
			String token = userService.doLogin(user);
			if(!StringUtils.isEmpty(token)) {
				//用户操作正确,将数据保存到cookie中
				Cookie cookie = new Cookie("TT_TICKET",token);
				cookie.setDomain("com.tt");		//标识共享的权限
				cookie.setMaxAge(7*24*3600);	//单位秒
				cookie.setPath("/");			//代表Cookie的权限
				response.addCookie(cookie);
				return new JsonResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult("用户名密码不匹配");
	}
}
