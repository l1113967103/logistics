package com.tt.common.web;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 编写拦截器对象,对控制层登陆方法进行访问拦截
 * 说明:拦截器编写好以后需要对拦截器进行配置
 * @author Administrator
 */
public class HandlerAccessInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 此方法在控制层(后端控制器)执行之前执行
	 * ,此方法的返回值决定了,请求是否继续传递
	 */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//    		throws Exception {
//    	System.out.println("preHandle");
//    	//获取当前系统的日历对象(时区,年月日,时分秒)
//    	Calendar c=Calendar.getInstance();
//    	c.set(Calendar.HOUR_OF_DAY,9);
//    	c.set(Calendar.MINUTE,0);
//    	c.set(Calendar.SECOND,0);
//    	//获取起始时间
//    	long start=c.getTimeInMillis();
//    	c.set(Calendar.HOUR_OF_DAY,18);
//    	//获取终止时间
//    	long end=c.getTimeInMillis();
//    	//获取当前时间
//    	long time=System.currentTimeMillis();
//    	if(time<start||time>end)
//    	throw new ServiceException("不在访问时间之内:8:00~17:00");
//    	return true;//true表示放行,false表示拦截
//    }
}


