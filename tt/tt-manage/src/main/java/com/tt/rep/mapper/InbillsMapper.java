package com.tt.rep.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tt.pojo.Inbills;
import com.tt.pojo.Vehicle;

public interface InbillsMapper extends BaseMapper<Inbills>{

	/**分页查询*/
	@Select("select * from inbills order by created_time desc limit #{startIndex},#{rows}")
	List<Inbills> selectInbillsByPage(@Param("startIndex")Integer startIndex,@Param("rows") Integer rows);
	
}
