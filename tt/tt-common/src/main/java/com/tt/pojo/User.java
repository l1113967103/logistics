package com.tt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("user")
public class User extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer id;	//主键自增
	private String username;
	private String password;
	private String phone;
	private String email;
}
