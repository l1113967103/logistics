package com.tt.sys.mapper;
import java.util.List;
import java.util.Map;

import com.tt.common.vo.Node;
import com.tt.pojo.SysDept;

public interface SysDeptMapper {
	int updateObject(SysDept entity);
	int insertObject(SysDept entity);
	List<Node> findZTreeNodes();
	List<Map<String,Object>> findObjects();
	int getChildCount(Integer id);
	int deleteObject(Integer id);
}
