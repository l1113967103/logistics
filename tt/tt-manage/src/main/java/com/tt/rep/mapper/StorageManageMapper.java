package com.tt.rep.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.StorageManage;

public interface StorageManageMapper extends BaseMapper<StorageManage>{

	@Select("select * from storage_manage where id=#{storageId}")
	List<StorageManage> findStorageManageByStorageId(@Param("storageId") Integer storageId);
	@Select("select * from storage_manage limit #{startIndex},#{pageSize}")
	List<StorageManage> findStorageManageByPage(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
}
