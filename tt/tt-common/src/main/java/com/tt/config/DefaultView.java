package com.tt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class DefaultView implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(!registry.hasMappingForPattern("/static/**")){
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		}
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	/**
	 * 	默认页面为starter页面
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("starter");//默认访问页面
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);//最先执行过滤
		WebMvcConfigurer.super.addViewControllers(registry);
	}
}
