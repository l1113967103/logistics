package com.tt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("order")
public class Order extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer id;
    private Integer inputId;
    private Integer outId;
    private Integer userId;
    private Integer orderDistrId;
    private String sender;
    private String commodityType;
    private String senderAddr;
    private String senderTel;
    private String receiver;
    private String receiverAddr;
    private String receiverTel;
}
