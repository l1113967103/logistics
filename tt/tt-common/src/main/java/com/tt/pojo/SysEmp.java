package com.tt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("sys_emps")
public class SysEmp extends BaseEntity{
	@TableId(type = IdType.AUTO)
	private Integer EmpId;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String mobile;
	private Integer valid=1;
	private Integer deptId;
	
	
}
