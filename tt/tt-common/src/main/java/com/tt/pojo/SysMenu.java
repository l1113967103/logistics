package com.tt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class SysMenu extends BaseEntity{
	private Integer id;
	/**菜单名称*/
	private String name;
	/**菜单url: log/doFindPageObjects.do*/
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
