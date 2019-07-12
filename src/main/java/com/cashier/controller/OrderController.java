package com.cashier.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.entity.Member;
import com.cashier.entity.Order;
import com.cashier.entity.OrderProduct;
import com.cashier.entity.Product;
import com.cashier.entityDTO.TopTenProductDTO;
import com.cashier.entityVo.OrderProductVo;
import com.cashier.entityVo.OrderVo;
import com.cashier.entityVo.Productvos;
import com.cashier.service.OrderProductService;
import com.cashier.service.OrderService;
import com.cashier.service.ProductService;
import com.cashier.util.codnoutil;

/**
 *
 * @ClassName: OrderController
 * @description 订单表的Controller层
 *
 * @author dujiawei
 * @createDate 2019年6月19日
 */
@Controller
public class OrderController {

	@Resource
	private OrderService orderService;
	@Resource
	private OrderProductService orderProductService;
	@Resource
	private ProductService productService;

	/**
	 * @Title: ZtorderManagement
	 * @description 跳转订单列表页面
	 * @param @param model
	 * @return String
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping("/ZtorderManagement")
	public String ZtorderManagement(Model model) {

		return "/views/entireManage/orderManage/orderManagement";
	}

	/**
	 * @Title: listOrderByOption
	 * @description 条件查询订单数量
	 * @param @param model
	 * @param @param page
	 * @param @param limit
	 * @param @param orderVo
	 * @param @param session
	 * @return Map<String,Object>
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping("/listOrderByOption")
	@ResponseBody
	public Map<String, Object> listOrderByOption(Model model, Integer page, Integer limit, OrderVo orderVo,
			HttpSession session) {
		BigInteger shopId = (BigInteger) session.getAttribute("shopId");

		orderVo.setPage((page - 1) * limit);
		orderVo.setLimit(limit);
		orderVo.setShopId(shopId);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Order> list = orderService.listOrderByOption(orderVo);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCount(0);
			}
		}
		;
		OrderVo oVo = orderService.countOrderByOption(orderVo);// 订单数量
		int count = 0;
		if (oVo.getCount() != 0) {
			count = oVo.getCount();
		}
		;

		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", list);
		result.put("count", count);

		return result;
	}
	@RequestMapping("/applistOrderByOption")
	@ResponseBody
	public Map<String, Object> applistOrderByOption(Model model, Integer page, Integer limit, OrderVo orderVo,BigInteger shopId) {
		//BigInteger shopId = (BigInteger) session.getAttribute("shopId");

		orderVo.setPage((page - 1) * limit);
		orderVo.setLimit(limit);
		orderVo.setShopId(shopId);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Order> list = orderService.listOrderByOption(orderVo);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCount(0);
			}
		}
		;
		OrderVo oVo = orderService.countOrderByOption(orderVo);// 订单数量
		int count = 0;
		if (oVo.getCount() != 0) {
			count = oVo.getCount();
		}
		;

		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", list);
		result.put("count", count);

		return result;
	}

	/**
	 * @Title: saveOrder
	 * @description 新增一条订单
	 * @param @param order
	 * @param @param session
	 * @param @param model
	 * @return Map<String,Object>
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	

	@RequestMapping("/saveOrder")
	@ResponseBody
	public Map<String, Object> saveOrder(Order order, HttpSession session, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		BigInteger shopId = (BigInteger) session.getAttribute("shopId");
		 order.setShopId(shopId);
		int num = orderService.saveOrder(order);
		if (num == 1) {
			map.put("code", 0);
			map.put("msg", "Success");

		} else {
			map.put("code", 1);
			map.put("msg", "新增订单失败！");
		}
		return map;
	}

	/**
	 * @Title: getSumOrderAndSumOrderMoney
	 * @description 查询订单量和订单总金额
	 * @param @param orderVo
	 * @return Object
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping(value = "/getSumOrderAndSumOrderMoney")
	@ResponseBody
	public Object getSumOrderAndSumOrderMoney(OrderVo orderVo) {
		OrderVo order = orderService.getSumOrderAndSumOrderMoney(orderVo);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "Success");
		// JSONArray array = (JSONArray) JSONArray.toJSON(member);
		result.put("data", order);

		return result;
	}

	/**
	 * @Title: ZtupdateOrderState
	 * @description 跳转修改订单状态（退货）页面
	 * @param @param model
	 * @return String
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping("/ZtupdateOrderState")
	public String ZtupdateOrderState(Model model) {

		return "/views/entireManage/memberManage/updateMember";
	}

	/**
	 * @Title: updateOrderState
	 * @description 修改订单状态（退货）
	 * @param @param order
	 * @return Map<String,Object>
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@RequestMapping("/updateOrderState")
	@ResponseBody
	public Map<String, Object> updateOrderState(Order order) {
		int num = orderService.updateOrderState(order);
		Map<String, Object> map = new HashMap<String, Object>();
		if (num == 1) {
			map.put("code", 0);
			map.put("msg", "Success");
		} else {
			map.put("code", 1);
			map.put("msg", "修改状态失败了");
		}

		return map;
	}

	/**
	 * 查询会员卡信息
	 * 
	 */
	@RequestMapping("/Querymembershipstatus")
	@ResponseBody
	public Map<String, Object> Enquiriesmembershipcard(String number, String phone, HttpSession session) {
		Member Member = orderService.Querymembershipstatus(number, phone);
		BigInteger shopId = (BigInteger) session.getAttribute("shopId");
		//shopId=new BigInteger("1");
		String shopName = (String) session.getAttribute("shopName");
		String orderNumber = codnoutil.cood(shopId);// 生成订单编号
		Order Order = new Order();
		/*
		 * Order.setBankName(bankName); Order.setCardNumber(cardNumber);
		 */
		
		Order.setNumber(orderNumber);
		Order.setShopId(shopId);
		Order.setShopName(shopName);
		Order.setPayMethod(1); 
		Order.setPayAdvance(new BigDecimal(0));
		 //Order.setRemark(remark); 
		 Order.setShopName(shopName); 
		 Order.setState(1);
		 Order.setTotalMoney(new BigDecimal(0));
		 
		

		
		Map<String, Object> map = new HashMap<String, Object>();
		if (Member == null) {
			
			map.put("code", 1);
			map.put("msg", "不是会员");
			map.put("data", orderNumber);
		} else {
			map.put("code", 0);
			map.put("msg", "是会员");
			map.put("data", orderNumber);
			map.put("Member", Member);
			Order.setMemberNumber(Member.getNumber());
		}
		int fig = orderService.saveOrder(Order);
		return map;

	}

	/**
	 * 查询商品信息
	 */
	@RequestMapping("/quershopping")
	@ResponseBody
	public Map<String, Object> quershopping(String barCode, HttpSession session) {
		Product product = orderService.querPreferences(barCode);
		Map<String, Object> map = new HashMap<String, Object>();
		if (product == null) {
			map.put("code", -1);
			map.put("msg", "商品没有录入信息");
			return map;
		}
		if (product.getState() == 2 || product.getCanUse() == 2) {
			map.put("code", -1);
			map.put("msg", "商品已下架");
			return map;
		}
		if (product != null) {
			map.put("code", 0);
			map.put("msg", "Success");
			map.put("data", product);
		}

		return map;
	}

	/**
	 * 查询会员商品实际付款金额
	 */
	@RequestMapping("/EnquiryonPreferentialPrice")
	@ResponseBody
	public Map<String, Object> EnquiryonPreferentialPrice(String barCode, Integer productCount, HttpSession session) {
		BigInteger shopId = (BigInteger) session.getAttribute("shopId");
		//shopId=new BigInteger("1");
		Product product = orderService.querPreferences(barCode);
		BigDecimal ser = new BigDecimal(Integer.parseInt(productCount.toString()));
		Map<String, Object> map = new HashMap<String, Object>();
		BigDecimal Discount = orderService.querDiscount(product.getId(), shopId,
				product.getMemberPrice().multiply(ser));
		if (product != null) {
			map.put("code", 0);
			map.put("msg", "Success");
			map.put("data", Discount);
		}

		return map;
	}

	/**
	 * 查询非会员商品实际付款金额
	 */
	@RequestMapping("/EnquiryonPreferential")
	@ResponseBody
	public Map<String, Object> EnquiryonPreferential(String barCode, Integer productCount, HttpSession session) {
		BigInteger shopId = (BigInteger) session.getAttribute("shopId");
		//shopId=new BigInteger("1");
		Product product = orderService.querPreferences(barCode);
		BigDecimal ser = new BigDecimal(Integer.parseInt(productCount.toString()));
		Map<String, Object> map = new HashMap<String, Object>();
		BigDecimal Discount = orderService.querDiscount(product.getId(), shopId, product.getSalePrice().multiply(ser));
		if (product != null) {
			map.put("code", 0);
			map.put("msg", "Success");
			map.put("data", Discount);
		}

		return map;
	}

	/**
	 * 会员购买商品
	 */
	@RequestMapping("/buyshopping")
	@ResponseBody
	public Map<String, Object> buyshopping(String orderNumber, String barCode, Integer productCount,
			HttpSession session, BigDecimal Discount) {
		BigInteger shopId = (BigInteger) session.getAttribute("shopId");
		//shopId=new BigInteger("1");
		// String orderNumber = codnoutil.cood(shopId);// 生成订单编号
		Product product = orderService.querPreferences(barCode);
		Map<String, Object> map = new HashMap<String, Object>();
		OrderProduct orderProduc = new OrderProduct();

		orderProduc.setProductId(product.getId());// 保存商品的id
		orderProduc.setProductCount(productCount);// 购买数量

		orderProduc.setMemberPricediscount(Discount);// 获取活动折扣价格和商品的满减价格
		orderProduc.setSalePrice(product.getSalePrice());// 获取单价
		orderProduc.setOrderNumber(orderNumber);
		BigDecimal ser = new BigDecimal(Integer.parseInt(productCount.toString()));
		orderProduc.setMemberPrice(product.getSalePrice().multiply(ser));
		orderProduc.setType(1);
		int fig = orderProductService.saveOrderProduct(orderProduc);
		try {
			
	
		orderProductService.upnumberbyid(product.getId(), productCount,shopId);
		} catch (Exception e) {
			map.put("code", 0);
			map.put("msg", "库存不足");
		}
		List<OrderProduct> OrderProduct = orderProductService.listorderNumberOrderProduct(orderNumber);
		if (fig > 0) {
			map.put("code", 0);
			map.put("msg", "Success");
			map.put("data", OrderProduct);

		} else {
			map.put("code", 1);
			map.put("msg", "下单失败");

		}
		return map;
	}
	/**
	 * 余额支付
	 */
	
	
	@RequestMapping("/buyshoppingpay")
	@ResponseBody
	public Map<String, Object> buyshoppingpay(String orderNumber) {
		Map<String, Object> map = new HashMap<String, Object>();				
		int fig=orderService.updatetotalMoney(1,orderNumber,"");
		
		if (fig > 0) {
			map.put("code", 0);
			map.put("msg", "Success");
			/* map.put("date", totalMoney); */

		} else {
			map.put("code", 1);
			map.put("msg", "下单失败");

		}
			return map;
	}

	/**
	 * @Title: getSumOrderAndSumOrderMoney
	 * @description 条件查询订单量和订单总金额（安卓端与pc端日报公用）
	 * @param @param orderVo
	 * @return Object
	 * @author chenshuxian
	 * @createDate 2019年7月8日
	 */
	@RequestMapping(value = "/getOrderAndSumOrderMoney")
	@ResponseBody
	public Map<String,Object> getOrderAndSumOrderMoney(OrderVo orderVo,HttpSession session) {
		orderVo.setShopId(new BigInteger(session.getAttribute("shopId")+""));
		orderVo.setState(1);
		OrderVo order = orderService.getSumOrderAndSumOrderMoney(orderVo);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 1);
		result.put("msg", "成功");
		// JSONArray array = (JSONArray) JSONArray.toJSON(member);
		result.put("data", order);

		return result;
	}
	/**
	 * 
	     * @Title: getPercentageOfProductType
	     * @description 获得每种商品种类下的商品今日销售百分比
	     * @param  店铺id
	     * @return   
	     * @author chenshuxian
	     * @createDate 2019年7月8日
	 */
	@RequestMapping("/getPercentageOfProductType")
	@ResponseBody
	public Map<String,Object> getPercentageOfProductType(HttpSession session){
		BigInteger shopId = new BigInteger(session.getAttribute("shopId")+"");
		Map<String,Object> map=orderService.getSumOrderByProductType(shopId);
		return map;
	}
	/**
	 * 
	     * @Title: getPercentageOfProductTypeByMonth
	     * @description 获得该月每种商品类型下商品的总销售额和销售数量
	     * @param  店铺id
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月9日
	 */
	@RequestMapping("/getPercentageOfProductTypeByMonth")
	@ResponseBody
	public Map<String,Object> getPercentageOfProductTypeByMonth(HttpSession session){
		BigInteger shopId = new BigInteger(session.getAttribute("shopId")+"");
		Map<String,Object> map = orderService.getMonthSumOrderByProductType(shopId);
		return map;
	}
	/**
	 * 
	     * @Title: getTopTenProduct
	     * @description  获得当月销售前十商品名称和销售额
	     * @param  店铺id
	     * @return    
	     * @author chenshuxian	
	     * @createDate 2019年7月9日
	 */
	@RequestMapping("/getTopTenProduct")
	@ResponseBody
	public Map<String,Object> getTopTenProduct(HttpSession session){
		Map<String,Object> map = new HashMap<>();
		BigInteger shopId = new BigInteger(session.getAttribute("shopId")+"");
		try {
			List<TopTenProductDTO> listProduct = orderService.getTopTenProduct(shopId);
			map.put("code", 1);
			map.put("msg", "成功");
			map.put("listProduct", listProduct);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 0);
			map.put("msg", "失败");
		}
		return map;
		
	}
	/**
	 * 根据订单号查询再次打印的方法app上使用
	 * @param model
	 * @param page
	 * @param limit
	 * @param orderVo
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/appdylistOrderByOption")
	@ResponseBody
	public Map<String, Object> appdylistOrderByOption(Model model, Integer page, Integer limit, OrderVo orderVo,BigInteger shopId) {
		//BigInteger shopId = (BigInteger) session.getAttribute("shopId");

		orderVo.setPage((1 - 1) * 20);
		orderVo.setLimit(20);
		orderVo.setShopId(shopId);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Order> list = orderService.listOrderByOption(orderVo);
		OrderProductVo orderProductVo=new OrderProductVo();
		orderProductVo.setOrderNumber(list.get(0).getNumber());
		orderProductVo.setPage((1-1)*20);
		orderProductVo.setLimit(20);
		List<OrderProductVo> list2 = orderProductService.listOrderProduct(orderProductVo);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCount(0);
			}
		}
		;
		OrderVo oVo = orderService.countOrderByOption(orderVo);// 订单数量
		int count = 0;
		if (oVo.getCount() != 0) {
			count = oVo.getCount();
		}
		;

		result.put("code", 1);
		result.put("msg", "Success");
		result.put("data", list);
		result.put("dates", list2);
		result.put("count", count);

		return result;
	}
}
