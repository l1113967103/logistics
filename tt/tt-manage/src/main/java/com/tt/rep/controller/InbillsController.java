package com.tt.rep.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;
import com.tt.common.vo.PageObject;
import com.tt.pojo.Inbills;
import com.tt.pojo.OrderDesc;
import com.tt.pojo.Storage;
import com.tt.rep.service.InbillsService;

@Controller
@RequestMapping("/rep")
public class InbillsController {

	@Autowired
	private InbillsService inbillsService;

	//显示库存页面进行入库出库操作
	@RequestMapping("/")
	@SuppressWarnings("unchecked")
	public String showRepertory(Model model) {
		Map<String, Object> map = inbillsService.showRepertory();
		for (String key : map.keySet()) {
			if("orderDesc".equals(key)) {
				PageObject<OrderDesc> pageOrderDesc = (PageObject<OrderDesc>) map.get(key);
				//先显示商品查询
				model.addAttribute("PageObject<OrderDesc>",pageOrderDesc);
			}
			if("storage".equals(key)) {
				PageObject<Storage> pageStorage = (PageObject<Storage>) map.get(key);
				//显示仓库查询
				model.addAttribute("PageObject<Storage>",pageStorage);
			}
		}
		return "跳转到生成入库单页面";
	}

	@RequestMapping("/")
	@ResponseBody
	public String createInbills(OrderDesc orderDesc,String inputPlace,Storage storage) {
		inbillsService.createInbills(orderDesc, inputPlace, storage);
		return "跳转到库存单查询页面";
	}
	/**分页查询*/
	@RequestMapping("/")
	@ResponseBody
	public JsonResult findInbillsByPage(Integer pageCurrent) {
		PageObject<Inbills> inbillsByPage = inbillsService.findInbillsByPage(pageCurrent);
		return new JsonResult(inbillsByPage);
	}

	/**删除入库单*/
	@RequestMapping("/")
	public String delInbills(Integer... ids) {
		int inbills = inbillsService.delInbills(ids);
		System.out.println("inbills:"+inbills);
		return "redirect:/分页查询页面";
	};

	/**修改入库单*/
	@RequestMapping("/")
	public String updateInbills(Inbills inbills) {
		int inbills2 = inbillsService.updateInbills(inbills);
		System.out.println("inbills2:"+inbills2);
		return "redirect:/分页查询页面";
	}
}
