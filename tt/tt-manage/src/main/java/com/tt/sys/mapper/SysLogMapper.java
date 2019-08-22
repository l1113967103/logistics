package com.tt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.SysLog;

public interface SysLogMapper extends BaseMapper<SysLog>{

	/**分页查询*/
	@Select("select * from sys_logs order by createdTime desc limit #{startIndex},#{rows}")
	List<SysLog> findPageObjects(@Param("startIndex")Integer startIndex,@Param("rows") Integer rows);

}
