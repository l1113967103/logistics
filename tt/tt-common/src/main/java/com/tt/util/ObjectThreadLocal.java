package com.tt.util;

public class ObjectThreadLocal {
	private static ThreadLocal<Class<?>> thread = new ThreadLocal<>();
	public static void setObject(Object obj) {

		thread.set(obj.getClass());
	}

	public static Object getObject() {

		return thread.get();
	}

	//使用threadLocal本地线程变量时,需要额外的注意内存泄漏问题.
	public static void remove() {

		thread.remove();	//移除threadLocal
	}


}
