package com.tt.pojo;

import java.util.Date;

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
	private Integer id;//订单id
	private String orderNumber;//订单号，可以使用随机生成
    private String sender;//寄件人姓名
    private String senderAddr;//寄件人地址
    private String senderTel;//寄件人电话
    private String receiver;//收件人姓名
    private String receiverAddr;//收件人地址
    private String receiverTel;//收件人电话
    private Date createTime;//创建订单时间
    private Date modifiedTime;//修改订单时间
    
}
