package com.tt.bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.Order;

public interface OrderMapper extends BaseMapper<Order>{

	//根据商品id查询订单id，根据订单id查询订单详情
//	@Select("select * from order where id=(select id from order_desc where id=#{id})")
//	Order findOrderByOrderDescId(@Param("id") Integer id);
	@Select("select * from order where order_number=#{orderNumber}")
	Order findOrderByOrderNumber(@Param("orderNumber")Integer orderNumber);

	List<Order> selectOrderNumberByPage(Integer startIndex, Integer pageSize);

}
