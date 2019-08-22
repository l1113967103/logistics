package com.tt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysRole extends BaseEntity{
	private Integer id;
	private String name;
	private String note;
	
}
