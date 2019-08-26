package com.tt.bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.OrderDesc;

public interface OrderDescMapper extends BaseMapper<OrderDesc>{

	/**分页查询全部商品信息,显示在生成入库单时的信息*/
	@Select("select * from order_desc order by modified_time desc limit #{startIndex},#{rows}")
	List<OrderDesc> findOrderDescByPage(@Param("startIndex")Integer startIndex,@Param("rows") Integer rows);
	//根据商品id查询订单id，根据订单id查询订单详情
//	@Select("select * from order where id=(select id from order_desc where id=#{id})")
//	Order findOrderByOrderDescId(@Param("id") Integer id);
}
