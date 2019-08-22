package com.tt.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BasePojo implements Serializable{
	private static final long serialVersionUID = 6925461729371221430L;
	private Date created;
	private Date updated;

}
