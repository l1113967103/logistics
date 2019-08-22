package com.tt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface PageMapper<T> {
	/**
	   * 	依据查询条件从起始位置startIndex获取
	   * 	当前页<T>信息
	   * 	@param username 查询条件
	   * 	@param startIndex 当前页起始位置
	   * 	@param pageSize 当前页的页面大小(
	   * 	当前页最多查询多少条件记录)
	   * 	@return 当前页的日志记录信息
	   */
	List<T> findPageObjects(
			  @Param("username")String username,
			  @Param("startIndex")Integer startIndex,
			  @Param("pageSize")Integer pageSize);
	/**
	   * 	依据条件统计用户行为<T>总数
	   * 	@param username 查询条件(操作用户)
	   * 	@return 总记录数
	   * 	说明:假如方法参数应用在动态SQL,两种方式:
	   * 	1)参数使用@Param进行定义(推荐)
	   * 	2)使用参数_paramter (不好记)-->了解
	   */
	int getRowCount(@Param("username")String username);
}
