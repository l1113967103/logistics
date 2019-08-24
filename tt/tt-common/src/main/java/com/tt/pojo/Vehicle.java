package com.tt.pojo;

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
public class Vehicle {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer transOrderId;
	private String name;
	private String type;
	private Integer status;//1.表示车辆正常。0.表示车辆在维修中
}