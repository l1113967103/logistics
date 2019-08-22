package com.tt.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable{
	private static final long serialVersionUID = -2823497274710739946L;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
