package com.tt;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tt.bus.service.OrderDescService;
import com.tt.bus.service.OrderService;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Order;
import com.tt.pojo.OrderDesc;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBus {

	@Autowired
	private OrderService orderService;
	//----------------对order操作
	/**分页查询订单*/
	@Test
	public void findAllOrder() {//mybatis的mapper中的接口不能重载，否则会报错
		PageObject<Order> findAllOrder = orderService.findAllOrder(null, 1);
		System.out.println(findAllOrder);
	}
	/**审核订单 */
//	@Test
//	public void verifyOrder() {
//		Order order = new Order(null, "1", 0, "李四", "北京", "1111111", "张三", "宁夏", "22222", 1);
//		order.setCreatedTime(new Date()).setModifiedTime(order.getCreatedTime());
//		int row = orderService.verifyOrder(order);
//		System.out.println(row);
//	}

	/**查询订单，为了生成订单时使用,生成入库*/
	@Test
	public void findOrder() {
		Order order = orderService.findOrder(1);
		System.out.println(order);
	}
	//----------------对orderDesc测试
	/**生成入库单时分页查询所有货物信息*/
	@Autowired
	private OrderDescService orderDescService;
	@Test
	public void findOrderDescByPage(){
		PageObject<OrderDesc> pageObject = orderDescService.findOrderDescByPage(1);
		System.err.println(pageObject);
	}
	/**出库时删除货物表信息*/
	@Test
	public void delOrderDesc() {
		int row = orderDescService.delOrderDesc(4);
		System.err.println(row);
	}
	//===================================================
}
