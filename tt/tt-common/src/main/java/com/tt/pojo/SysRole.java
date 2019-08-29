package com.tt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("sys_roles")
public class SysRole extends BaseEntity{
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String name;
	private String note;
	
}
