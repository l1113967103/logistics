package com.tt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;
import com.tt.pojo.SysRole;
import com.tt.sys.service.SysRoleService;


@RequestMapping("/role")
@Controller
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	@RequestMapping("/doRoleListUI")
	public String doRoleListUI() {
		return "sys/role_list";
	}
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent){
		return new JsonResult(sysRoleService.findObjects(name, pageCurrent));
	}
	/**删除角色信息*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		sysRoleService.deleteObject(id);
		return new JsonResult("delete Ok");
	}

	@RequestMapping("/doRoleEditUI")
	public String doRoleEditUI(){
		return "sys/role_edit";
	}
	/**添加角色信息*/
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysRole entity,Integer[] menuIds){
		sysRoleService.saveObject(entity,menuIds);
		return new JsonResult("save ok");    
	}
	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		return new JsonResult(sysRoleService.findObjectById(id));
	}
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysRole entity,Integer[] menuIds){
		sysRoleService.updateObject(entity, menuIds);
		return new JsonResult("update ok");
	}
	/**id，name用于user*/
	@RequestMapping("/doFindRoles")
	@ResponseBody
	public JsonResult doFindObjects(){
		return new JsonResult(sysRoleService.findObjects());
	}
}
