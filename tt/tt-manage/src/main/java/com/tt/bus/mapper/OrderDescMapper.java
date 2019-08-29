package com.tt.bus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.Order;
import com.tt.pojo.OrderDesc;

public interface OrderDescMapper extends BaseMapper<OrderDesc>{

	/**分页查询全部货物信息,显示在生成入库单时的信息*/
	@Select("select * from order_desc order by modified_time desc limit #{startIndex},#{rows}")
	List<OrderDesc> findOrderDescByPage(@Param("startIndex")Integer startIndex,@Param("rows") Integer rows);
	//通过orderId查询orderDesc
	@Select("select * from order_desc where tb_order_id=(select id from tb_order where id=#{id})")
	OrderDesc findOrderIdByOrderDesc(@Param("orderId") Integer orderId);
}
