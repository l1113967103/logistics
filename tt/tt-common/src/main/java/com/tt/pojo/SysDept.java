package com.tt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class SysDept extends BaseEntity{
	private Integer id;
	private String name;
	private Integer parentId;
	private Integer sort;
	private String note;
}
