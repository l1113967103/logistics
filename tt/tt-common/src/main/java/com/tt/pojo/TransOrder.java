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
@TableName("trans_order")
public class TransOrder extends BasePojo{

	@TableId(type = IdType.AUTO)
	private Integer id;//运输单id
	private Integer outbillsId;//出库单号
	private Integer orderDescId;//货物id
	private Integer driverId;//司机id
	private Integer vehicleId;//汽车id
	private String outPlace;//出发地
	private String processState;//运输过程状态(用于返回获取动态信息)
	private String destPlace;//目的地
	private Integer status;//状态1.表示在驾驶中。0.表示空闲司机
}
