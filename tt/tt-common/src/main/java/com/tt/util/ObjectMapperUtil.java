package com.tt.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *说明： 该工具类实现对象与json的互转
 *  static方法被对象直接调用
 * @author ASUS
 *
 */
//1.对象转换为json
public class ObjectMapperUtil {
	//成员变量是否有线程安全问题？
	//   private static final Integer a = 123;  //有
	private static final ObjectMapper objectMapper = new ObjectMapper();
	//static 静态方法不能调用非静态变量      final:不可修改
	public static String toJSON(Object target) {
		String result = null;
		try {
			result = objectMapper.writeValueAsString(target);
		} catch (Exception e) {
			e.printStackTrace();
			//或者打印日志
			throw new RuntimeException();
		}
		return result;
	}

	//2.json串转换为对象
	public static<T> T toObject(String json,Class<T> targetClass) {
		T target = null;
		try {
			target = objectMapper.readValue(json, targetClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return target;
	}
}
