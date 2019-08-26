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
	private Integer id;//商品ID
	private Integer orderId;//订单ID
	private Integer storageId;//仓库id
	private Integer storageManageId;//库存id
	private String name;//商品名称
	private String kind;//商品种类
	private Integer num;//商品数量
	private Integer weight;//商品重量
	private String bulk;//商品体积
	private Integer status;//0.未入库1.已入库2.已出库
	
}
