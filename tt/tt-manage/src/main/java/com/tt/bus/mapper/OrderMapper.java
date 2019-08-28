package com.tt.bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.Order;

public interface OrderMapper extends BaseMapper<Order>{

	//根据商品id查询订单id，根据订单id查询订单详情(为了生成运输单)
	@Select("select * from tb_order where id=(select tb_order_id from order_desc where id=#{id})")
	Order findOrderByOrderDescId(@Param("id") Integer id);
	/**web发送的订单号查询订单信息*/
	@Select("select * from tb_order where order_number=#{orderNumber}")
	Order findOrderByOrderNumber(@Param("orderNumber") String orderNumber);
	@Select("select * from tb_order limit #{startIndex},#{pageSize}")
	List<Order> findAllByPage(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
	@Select("select * from tb_order where order_number=#{orderNumber} limit #{startIndex},#{pageSize}")
	List<Order> findOrderNumberByPage(@Param("orderNumber") String orderNumber,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);

}
