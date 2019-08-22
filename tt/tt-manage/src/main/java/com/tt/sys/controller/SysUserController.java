package com.tt.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;
import com.tt.pojo.SysEmp;
import com.tt.sys.service.SysUserService;
@RequestMapping("/user")
@Controller
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	@RequestMapping("/doUserListUI")
	public String doUserListUI() {
		return "sys/user_list";
	}
	@RequestMapping("/doUserEditUI")
	public String doUserEditUI() {
		return "sys/user_edit";
	}
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		return new JsonResult(sysUserService.findPageObjects(username, pageCurrent));
	}
	/**
	 * 	用户的禁用与启用
	 * @param id
	 * @param valid
	 * @return
	 */
	@RequestMapping("/doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id,Integer valid){
		sysUserService.validById(id,valid,"admin");//"admin"用户将来是登陆用户
		return new JsonResult("update ok");
	}
	/**添加功能中的角色*/
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysEmp entity,Integer[] roleIds){
		sysUserService.saveObject(entity,roleIds);
		return new JsonResult("save ok");
	}
	@RequestMapping("/doLogin")
	@ResponseBody
	public JsonResult doLogin(boolean isRememberMe,String username,String password) {
		//1.获取Subject对象
		Subject subject = SecurityUtils.getSubject();
		//2.通过Subject提交用户信息,交给shiro框架进行认证操作
		//2.1对用户进行封装. username身份信息,password凭证信息
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		if(isRememberMe) {
			token.setRememberMe(true); 
		}
		//2.2对用户信息进行身份认证
		subject.login(token);
		//分析:
		//1)token会传给shiro的SecurityManager
		//2)SecurityManager将token传递给认证管理器
		//3)认证管理器会将token传递给realm
		return new JsonResult("login ok");
	}
	
	/**修改密码页面跳转*/
	@RequestMapping("/doPwdEditUI")
	public String doPwdEditUI() {
		return "sys/pwd_edit";
	}
	/**实现修改密码*/
	@RequestMapping("/doUpdatePassword")
	@ResponseBody
	public JsonResult doUpdatePassword(String oldPwd, String newPwd1,String newPwd2) {
		sysUserService.updatePassword(oldPwd, newPwd1, newPwd2);
		return new JsonResult("密码修改成功");
	}
}
