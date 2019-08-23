package com.tt.common.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
@Order(2)
@Aspect
@Service
public class SysCacheAspect {
	/**
	 * 当使用RequiredCache方法执行时就会执行,
	 * @Around这个注解修饰的方法
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(com.tt.annotation.RequiredCache)")
	public Object aroundMethod(ProceedingJoinPoint jp)
	throws Throwable{
		System.out.println("CacheAspect:先从缓存取数据");
		Object result=jp.proceed();
		
		return result;
	}
	
}
