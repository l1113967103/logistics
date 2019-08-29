package com.tt.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("vehicle")
public class Vehicle extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer transOrderId;//运输单id
	private String name;
	private String type;
	private Integer status;//0.表示车辆正常。1.表示车辆在维修中
	private Date createdTime;//创建时间
}
