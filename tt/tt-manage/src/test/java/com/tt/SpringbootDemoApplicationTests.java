package com.tt;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tt.pojo.SysLog;
import com.tt.sys.mapper.SysLogMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {
	@Autowired
	private SysLogMapper mapper;
	@Test
	public void contextLoads() {
//		List<SysLog> list = mapper.selectList(null);
//		System.out.println(list);
	}

}
