package com.tt.sys.service;
import java.util.List;
import java.util.Map;

import com.tt.common.vo.Node;
import com.tt.pojo.SysDept;

public interface SysDeptService {
	int saveObject(SysDept entity);
	int updateObject(SysDept entity);
	
	List<Node> findZTreeNodes();
	List<Map<String,Object>> findObjects();
	
	int deleteObject(Integer id);
}
