package com.tt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tt.common.vo.PageObject;
import com.tt.pojo.Inbills;
import com.tt.rep.service.InbillsService;
@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.tt.rep.mapper")
public class TestPage {

	@Autowired
	private InbillsService inbillsService;
	@Test
	public void testFindObjectByPage() {
		Inbills inbills = new Inbills(null, 1, null, null, null, null, null);
//		PageObject<Inbills> order = inbillsService.findAllOrder(inbills, 2201, 3);
//		System.out.println(order);
	}
}
