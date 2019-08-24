package com.tt.trans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tt.trans.service.TransOrderService;

@RestController
@RequestMapping("/trans")
public class TransOrderController {

	@Autowired
	private TransOrderService transOrderService;
}
