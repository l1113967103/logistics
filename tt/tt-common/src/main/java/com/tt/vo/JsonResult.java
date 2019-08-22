package com.tt.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult implements Serializable{
	private static final long serialVersionUID = 2985397766690202262L;
	/**状态码*/
	private int state=1;//1 表示ok,0表示error
	/**状态码对应的消息*/
	private String msg;
	/**页面上要具体呈现的数据*/
	private Object data;
	/**
	 * 	为了满足用户的调用需求,重载方法
	 */
	public static JsonResult ok() {
		return new JsonResult(1,"服务器调用成功",null);
	}
	public static JsonResult ok(Object data) {
		return new JsonResult(1,"服务器调用成功",data);
	}
	public static JsonResult ok(String msg,Object data) {
		return new JsonResult(1,msg,data);
	}
	public static JsonResult fail() {
		return new JsonResult(0, null, null);
	}
	public static JsonResult fail(String msg) {
		return new JsonResult(0, msg, null);
	}
}
