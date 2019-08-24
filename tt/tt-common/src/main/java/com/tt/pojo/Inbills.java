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
@TableName("inbills")
public class Inbills {

	@TableId(type = IdType.AUTO)
	private Integer id;//入库单id
	private String inputPlace;//入库地点
	private Integer orderDescId;//商品id
	private String commodityType;//商品类型
	private Integer orderNum;//订单数量
	private Date inputTime;//入库时间
}
