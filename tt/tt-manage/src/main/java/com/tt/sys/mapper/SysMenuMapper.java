package com.tt.sys.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.pojo.SysMenu;

public interface SysMenuMapper extends BaseMapper<SysMenu>{

	List<Map<String,Object>> findObjects();
}
