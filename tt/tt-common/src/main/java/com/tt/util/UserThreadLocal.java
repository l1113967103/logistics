package com.tt.util;

import com.tt.pojo.User;

public class UserThreadLocal {
	private static ThreadLocal<User> thread = new ThreadLocal<>();
	public static void setUser(User user) {

		thread.set(user);
	}

	public static User getUser() {

		return thread.get();
	}

	//使用threadLocal本地线程变量时,需要额外的注意内存泄漏问题.
	public static void remove() {

		thread.remove();	//移除threadLocal
	}


}
