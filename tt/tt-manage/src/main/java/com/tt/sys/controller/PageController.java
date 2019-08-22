package com.tt.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/indexUI")
	public String indexUI() {
		return "starter";
	}
	@RequestMapping("/pageUI")
	public String pageUI() {
		return "common/page";
	}

	@RequestMapping("/loginUI")
	public String loginUI(){
		return "login";
	}
}
