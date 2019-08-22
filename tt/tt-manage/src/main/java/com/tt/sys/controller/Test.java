package com.tt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {

	@RequestMapping("/show")
	public String show() {
		return "test";
	}
}
