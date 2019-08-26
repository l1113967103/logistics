package com.tt.util;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Vehicle;

public class FindObjectByPage {

	public static <T> PageObject<T> find(String name,Integer pageCurrent,BaseMapper<T> mapper){
		QueryWrapper<T> queryWrapper = new QueryWrapper<>();
		Integer pageSize=5;//每页5条数据
		Integer startIndex = (pageCurrent-1)*pageSize;
		//查询汽车总数量
		Integer count = 0;
		/**当前页要呈现的记录*/
		List<T> records = null;
		if(null==name||"".equals(name)) {
			count = mapper.selectCount(null);//查询所有汽车数量
			if(count==0)
				throw new RuntimeException("没有车辆信息");
//			records = mapper.selectVehicleByPage(startIndex, pageSize);
		}else {
			queryWrapper.like("name", name);
			count = mapper.selectCount(queryWrapper);//根据汽车名称查询所有汽车数量
			if(count==0)
				throw new RuntimeException("没有车辆信息");
//			records = mapper.selectVehicleByName(name,startIndex, pageSize);
		}
		int pageCount=(count-1)/pageSize+1;
		return new PageObject<>(records, count, pageCount, pageCurrent, pageSize);
	}
}
