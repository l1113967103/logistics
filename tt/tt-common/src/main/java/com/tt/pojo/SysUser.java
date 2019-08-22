package com.tt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysUser extends BaseEntity{
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String mobile;
	private Integer valid=1;
	private Integer deptId;
	
	
}
