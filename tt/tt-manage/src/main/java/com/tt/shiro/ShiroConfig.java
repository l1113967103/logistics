package com.tt.shiro;

import java.util.LinkedHashMap;

import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		// 配置不会被拦截的链接 顺序判断
		
		filterChainDefinitionMap.put("/bower_components/**","anon");
		filterChainDefinitionMap.put("/build/**","anon");
		filterChainDefinitionMap.put("/bus/**","anon");
		filterChainDefinitionMap.put("/commons/**","anon");
		filterChainDefinitionMap.put("/css/**","anon");
		filterChainDefinitionMap.put("/dist/**","anon");
		//filterChainDefinitionMap.put("/fonts/**","anon");
		//filterChainDefinitionMap.put("/images/**","anon");
		filterChainDefinitionMap.put("/js/**","anon");
		filterChainDefinitionMap.put("/plugins/**","anon");
		filterChainDefinitionMap.put("/rep/**","anon");
		filterChainDefinitionMap.put("/sys/**","anon");
		//filterChainDefinitionMap.put("/tasks/**","anon");
		filterChainDefinitionMap.put("/test/**","anon");
		filterChainDefinitionMap.put("/trans/**","anon");
		filterChainDefinitionMap.put("/user/doLogin","anon");
		
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/doLogout","logout");
		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		
		filterChainDefinitionMap.put("/**", "user");
		
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/doLoginUI");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/doIndexUI");

		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	@Bean 
	public ShiroUserRealm myShiroRealm(){ 
		ShiroUserRealm myShiroRealm = new ShiroUserRealm();
		return myShiroRealm; }
	@Bean 
	public org.apache.shiro.mgt.SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm()); 
		return securityManager; 
	}
}

