package com.tt.rep.service;

import java.util.List;

import com.tt.common.vo.PageObject;
import com.tt.pojo.Storage;

public interface StorageService {
	/**分页查询仓库信息,根据仓库名称分页*/
	PageObject<Storage> findStorageByPage(Integer name,Integer pageCurrent);
	/**新增仓库信息*/
	int addStorage(Storage storage);
	/**修改仓库信息*/
	int updateStorage(Storage storage);
	/**删除仓库信息*/
	int delStorage(Integer... storageId);
	
	List<Storage> findAllStorage();
}
