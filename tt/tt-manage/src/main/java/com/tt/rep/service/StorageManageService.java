package com.tt.rep.service;

import com.tt.common.vo.PageObject;
import com.tt.pojo.StorageManage;

public interface StorageManageService {

	/**库存分页查询全部信息*/
	PageObject<StorageManage> findStorageManageByPage(Integer pageCurrent);
	/**当出库的时候删除库存*/
	int delStorageManage(Integer... ids);
	/**修改库存*/
	int updateStorageManage(StorageManage storageManage);
}
