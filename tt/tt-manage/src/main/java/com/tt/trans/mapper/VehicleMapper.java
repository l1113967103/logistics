package com.tt.trans.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.Vehicle;

public interface VehicleMapper extends BaseMapper<Vehicle>{

	/**分页查询*/
	@Select("select * from vehicle order by created_time desc limit #{startIndex},#{rows}")
	List<Vehicle> selectVehicleByPage(@Param("startIndex")Integer startIndex,@Param("rows") Integer rows);
	/**分页查询按照车名*/
	@Select("select * from vehicle order by created_time desc limit #{startIndex},#{rows} where name=#{name}")
	List<Vehicle> selectVehicleByName(@Param("name") String name,@Param("startIndex")Integer startIndex,@Param("rows") Integer rows);
}
