package com.tt.rep.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.Outbills;

public interface OutbillsMapper extends BaseMapper<Outbills>{

	@Select("select * from outbills order by created_time desc limit #{startIndex},#{rows}")
	List<Outbills> selectOutbillsByPage(@Param("startIndex")Integer startIndex,@Param("rows") Integer rows);

}
