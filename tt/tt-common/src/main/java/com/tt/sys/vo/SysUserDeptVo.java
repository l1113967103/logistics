package com.tt.sys.vo;

import com.tt.pojo.BaseEntity;
import com.tt.pojo.SysDept;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class SysUserDeptVo extends BaseEntity{
	private static final long serialVersionUID = 951138124475981917L;
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String mobile;
	private Integer valid=1;
	private SysDept sysDept; //private Integer deptId;
	
}
