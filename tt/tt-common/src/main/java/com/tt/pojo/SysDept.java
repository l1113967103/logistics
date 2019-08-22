package com.tt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@TableName("sys_depts")
public class SysDept extends BaseEntity{
	@TableId(type = IdType.AUTO)
	private Integer deptId;
	private String name;
	private Integer parentId;
	private Integer sort;
	private String note;
}
