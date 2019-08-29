package com.tt.sys.vo;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ShowTransOrder {

	private Integer transOrderId;//运输单号
	private String driverName;//司机名称
	private String vehicleName;//车辆名称
	private String outPlace;//出发地
	private String destPlace;//目的地
	private Integer status;//运输状态1.运输完成0.运输中
	private Date createdTime;//创建时间
}
