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
@TableName("order_desc")
public class OrderDesc extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer id;//货物ID
	private Integer tbOrderId;//订单ID
	private Integer storageId;//仓库id
	private Integer storageManageId;//库存id
	private String name;//货物名称
	private String kind;//货物种类
	private Integer num;//货物数量
	private Integer weight;//货物重量
	private String bulk;//货物体积
	private Integer status;//0.未入库1.已入库2.已出库
	
}
