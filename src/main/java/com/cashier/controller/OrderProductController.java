package com.cashier.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cashier.entity.OrderProduct;
import com.cashier.entityVo.OrderProductVo;
import com.cashier.service.OrderProductService;

/**
 *
 * @ClassName: OrderProductController
 * @description 订单商品表的控制层
 *
 * @author dujiawei
 * @createDate 2019年6月19日
 */
@Controller
public class OrderProductController {
	
	@Resource
	private OrderProductService orderProductService;
	
	
	/**
	 * @Title: ZtorderProductManagement
	 * @description 跳转订单商品列表页面
	 * @param @param model
	 * @return String    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping("/ZtorderProductManagement")
	public String ZtorderProductManagement(Model model){
		
		return "/views/entireManage/orderManage/orderManagement";
	}
	
	/**
	 * @Title: listOrderProduct
	 * @description 1、订单商品列表
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param orderProductVo
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping("/listOrderProduct")
	@ResponseBody
	public Map<String,Object> listOrderProduct(Model model, Integer page, Integer limit, OrderProductVo orderProductVo, HttpSession session) {
		orderProductVo.setPage((page-1)*limit);
		orderProductVo.setLimit(limit);
		Map<String , Object> result = new HashMap<String , Object>();
		List<OrderProductVo> list = orderProductService.listOrderProduct(orderProductVo);
		if(list.size() > 0) {
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setCount(0);
			}
		};
		OrderProductVo opVo = orderProductService.countOrderProduct(orderProductVo);//订单商品数量
		int count = 0;
		if(opVo.getCount() != 0) {
			count = opVo.getCount();
		};
		
		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", list);
		result.put("count", count);
		
		return result;	
	}
	
	/**
	 * @Title: saveOrderProduct
	 * @description 2、新增一条订单商品
	 * @param @param orderProduct
	 * @param @param session
	 * @param @param model
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping("/saveOrderProduct")
	@ResponseBody
	public Map<String, Object> saveOrderProduct(OrderProduct orderProduct, HttpSession session ,Model model) {
		Map<String , Object> map = new HashMap<String , Object>();
		int num = orderProductService.saveOrderProduct(orderProduct);
		if(num == 1){
			map.put("code", 0);
			map.put("msg", "Success");
			
		} else {
			map.put("code", 1);
			map.put("msg", "新增订单商品失败！");
		}
		return map;
	}
	
	/**
	 * @Title: delOrderProduct
	 * @description 3、删除一条订单商品
	 * @param @param orderProduct
	 * @param @param session
	 * @param @param model
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping("/delOrderProduct")
	@ResponseBody
	public Map<String, Object> delOrderProduct(OrderProduct orderProduct, HttpSession session ,Model model) {
		Map<String , Object> map = new HashMap<String , Object>();
		int num = orderProductService.delOrderProduct(orderProduct);
		if(num == 1){
			map.put("code", 0);
			map.put("msg", "Success");
			
		} else {
			map.put("code", 1);
			map.put("msg", "删除订单商品失败！");
		}
		return map;
	}
	
	/**
	 * @Title: listOrderProductByOrderNumber
	 * @description 4、通过订单号查询订单商品信息
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param orderProductVo
	 * @param @param session
	 * @return Map<String,Object>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping("/listOrderProductByOrderNumber")
	@ResponseBody
	public Map<String,Object> listOrderProductByOrderNumber(Model model, Integer page, Integer limit, OrderProductVo orderProductVo, HttpSession session) {
		orderProductVo.setPage((page-1)*limit);
		orderProductVo.setLimit(limit);
		Map<String , Object> result = new HashMap<String , Object>();
		List<OrderProductVo> list = orderProductService.listOrderProductByOrderNumber(orderProductVo);
		if(list.size() > 0) {
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setCount(0);
			}
		};
		OrderProductVo opVo = orderProductService.countOrderProductByOrderNumber(orderProductVo);//订单商品数量
		int count = 0;
		if(opVo.getCount() != 0) {
			count = opVo.getCount();
		};
		
		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", list);
		result.put("count", count);
		
		return result;	
	}
		
	

}
