package com.tt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDesc {

	private Integer id;//商品ID
	private Integer orderId;//订单ID
	private String commodityType;//商品名称
	private String kind;//商品种类
	private Integer num;//商品数量
	private Integer weight;//商品重量
	private String bulk;//商品体积
	
}
