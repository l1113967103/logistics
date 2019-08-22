package com.tt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@TableName("sys_menus")
public class SysMenu extends BaseEntity{
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**菜单名称*/
	private String name;
	/**菜单url: log/doFindPageObjects
	private String url;
	/**菜单类型(两种:按钮,普通菜单)*/
	private Integer type;
	/**排序(序号)*/
	private Integer sort;
	/**备注*/
	private String note;
	/**上级菜单id*/
	private Integer parentId;
	/**菜单对应的权限标识(sys:log:delete)*/
	private String permission;
}
