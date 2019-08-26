package com.tt.rep.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.Storage;

public interface StorageMapper extends BaseMapper<Storage>{
	
	@Select("select * from storage limit #{startIndex},#{pageSize}")
	List<Storage> selectStorageNameByPage(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
	
	@Select("select * from storage where name=#{name} limit #{startIndex},#{pageSize}")
	List<Storage> selectStorageNameByPage(@Param("name") Integer name,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);

}
