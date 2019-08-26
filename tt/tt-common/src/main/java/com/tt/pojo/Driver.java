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
@TableName("driver")
public class Driver extends BasePojo{

	@TableId(type = IdType.AUTO)
	private Integer id;//司机id
	private String name;//司机姓名
	private Integer status;//司机状态1.表示有匹配的车辆。0.表示司机处于闲余状态
}
