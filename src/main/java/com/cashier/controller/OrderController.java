package com.cashier.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.cashier.dao.UserOperationMapper;
import com.cashier.entity.Member;
import com.cashier.entity.Order;
import com.cashier.entity.OrderProduct;
import com.cashier.entity.Product;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.entityDTO.TopTenProductDTO;
import com.cashier.entityVo.OrderProductVo;
import com.cashier.entityVo.OrderVo;
import com.cashier.entityVo.Productvos;
import com.cashier.service.OrderProductService;
import com.cashier.service.OrderService;
import com.cashier.service.ProductService;
import com.cashier.util.codnoutil;
import com.cashier.util.pay.util.JsonUtil;
import com.cashier.util.pay.util.Main;
import com.cashier.util.pay.util.WechatPayConstants;
import com.cashier.util.pay.util.alipaySweepPaymentcontroller;

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
	private static Log log = LogFactory.getLog(OrderController.class);
	@Resource
	private OrderService orderService;
	@Resource
	private OrderProductService orderProductService;
	@Resource
	private ProductService productService;
	@Resource
	private UserOperationMapper userOperationMapper;

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
	@RequiresPermissions("/listOrderByOption")
	@ResponseBody
	public Map<String, Object> listOrderByOption(Model model, Integer page, Integer limit, OrderVo orderVo,
			HttpSession session) {
	    if(orderVo.getShopId()==null){
            BigInteger shopId = (BigInteger) session.getAttribute("shopId");
            orderVo.setShopId(shopId);
        }
		orderVo.setPage((page - 1) * limit);
		orderVo.setLimit(limit);
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
	public Map<String, Object> applistOrderByOption(Model model, Integer page, Integer limit, OrderVo orderVo,
			BigInteger shopId) {
		// BigInteger shopId = (BigInteger) session.getAttribute("shopId");

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
			 // 添加一条操作记录
            User user = (User)session.getAttribute("user");
            UserOperation userOperation = new UserOperation();
            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
            userOperation.setUserName(user.getUsername());
            userOperation.setName(user.getName());
            userOperation.setOperatingContent("新增一条订单");
            userOperationMapper.saveUserOperation(userOperation);
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
	 * @throws Exception 
	 * @createDate 2019年6月19日
	 */
	@RequestMapping(value = "/updateOrderState", produces = "application/json; charset=utf-8")

	@ResponseBody
	public String  updateOrderState(Order order,String outTradeNo,String refundAmount,HttpSession session) throws Exception {
		if(order.getPayMethod()==4) {
		AlipayF2FRefundResult result = alipaySweepPaymentcontroller.test_trade_refund(outTradeNo, refundAmount);
		switch (result.getTradeStatus()) {
        case SUCCESS:
        	int num = orderService.updateOrderState(order);
            log.info("支付宝退款成功: )");
            return JsonUtil.getResponseJson(1, "退款成功", null, null);

        case FAILED:
            log.error("支付宝退款失败!!!");
            return JsonUtil.getResponseJson(-1, result.getResponse().getSubMsg(), null, null);
        

        case UNKNOWN:
            log.error("系统异常，订单退款状态未知!!!");
            return JsonUtil.getResponseJson(-1, result.getResponse().getSubMsg(), null, null);

        default:
            log.error("不支持的交易状态，交易返回异常!!!");
            return JsonUtil.getResponseJson(-1, result.getResponse().getSubMsg(), null, null);
    }
		}
		if(order.getPayMethod()==3) {
			WechatPayConstants WechatPayConstants = new WechatPayConstants();
			
			Map<String, String> result = WechatPayConstants.drawback(outTradeNo, refundAmount, refundAmount);
		     String 	return_code = result.get("result_code");
			if (!"SUCCESS".equals(return_code)) {
				// 支付失败
				System.out.println("通讯失败>>" + result);
				return JsonUtil.getResponseJson(-1, result.get("err_code_des"), null, null);
			}

			// 支付成功
			
			int num = orderService.updateOrderState(order);
			System.out.println("退款查询接口" + result);

			return JsonUtil.getResponseJson(1, "微信退款成功", null, null);

		}else {
			
			int num = orderService.updateOrderState(order);
			Map<String, Object> map = new HashMap<String, Object>();
			if (num == 1) {
				 // 添加一条操作记录
	            User user = (User)session.getAttribute("user");
	            UserOperation userOperation = new UserOperation();
	            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
	            userOperation.setUserName(user.getUsername());
	            userOperation.setName(user.getName());
	            userOperation.setOperatingContent("修改订单状态（退款）");
	            userOperationMapper.saveUserOperation(userOperation);
				map.put("code", 0);
				map.put("msg", "Success");
			} else {
				map.put("code", 1);
				map.put("msg", "修改状态失败了");
			}

		}
		
	  return JsonUtil.getResponseJson(1, "退款成功", null, null);
	}

	/**
	 * 查询会员卡信息
	 * 
	 */
	@RequestMapping("/Querymembershipstatus")
	@ResponseBody
	public Map<String, Object> Enquiriesmembershipcard(String number, String phone, HttpSession session) {
		Member member = orderService.Querymembershipstatus (phone);
		BigInteger shopId = (BigInteger) session.getAttribute("shopId");

		Map<String, Object> map = new HashMap<String, Object>();
		if (member == null) {

			map.put("code", -1);
			map.put("msg", "不是会员");
			
			/* map.put("data", orderNumber); */
		} else {
			if(member.getState()!=2) {
				map.put("code", -1);
				map.put("msg", "会员状态不正确");
			
				
			}else {
				map.put("code", 0);
				map.put("msg", "是会员");
				
				map.put("Member", member);
			}
		
			
		}

		return map;

	}

	/**
	 * 查询商品信息
	 */
	@RequestMapping("/quershopping")
	@ResponseBody
	public Map<String, Object> quershopping(String barCode, HttpSession session) {
		 BigInteger shopId = (BigInteger) session.getAttribute("shopId");
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
			map.put("code", 1);
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
	public Map<String, Object> EnquiryonPreferentialPrice(String barCode, HttpSession session, int type) {
		 BigInteger shopId = (BigInteger) session.getAttribute("shopId");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, BigDecimal> maps = orderService.querDiscount(barCode, shopId);
		if (type == 1) {
			map.put("code", 0);
			map.put("msg", "Success");
			map.put("data", maps.get("vipdiscounts"));
		} else {
			map.put("code", 0);
			map.put("msg", "Success");
			map.put("data", maps.get("discounts"));
		}

		return map;
	}

	/*
	
	

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
	public Map<String, Object> getOrderAndSumOrderMoney(OrderVo orderVo, HttpSession session) {
		orderVo.setShopId(new BigInteger(session.getAttribute("shopId") + ""));
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
	 * @description 获得今日每种商品类型下商品的总销售额和销售数量以及百分比
	 * @param 店铺id
	 * @return
	 * @author chenshuxian
	 * @createDate 2019年7月8日
	 */
	@RequestMapping("/getPercentageOfProductType")
	@ResponseBody
	public Map<String, Object> getPercentageOfProductType(HttpSession session) {
		System.out.println(session.getAttribute("shopId"));
		BigInteger shopId = new BigInteger(session.getAttribute("shopId") + "");
		Map<String, Object> map = orderService.getSumOrderByProductType(shopId);
		return map;
	}

	/**
	 * 
	 * @Title: getPercentageOfProductTypeByMonth
	 * @description 获得该月每种商品类型下商品的总销售额和销售数量以及百分比
	 * @param 店铺id
	 * @return
	 * @author chenshuxian
	 * @createDate 2019年7月9日
	 */
	@RequestMapping("/getPercentageOfProductTypeByMonth")
	@ResponseBody
	public Map<String, Object> getPercentageOfProductTypeByMonth(HttpSession session) {
		BigInteger shopId = new BigInteger(session.getAttribute("shopId") + "");
		Map<String, Object> map = orderService.getMonthSumOrderByProductType(shopId);
		return map;
	}

	/**
	 * 
	 * @Title: getTopTenProduct
	 * @description 获得当月销售前十商品名称和销售额
	 * @param 店铺id
	 * @return
	 * @author chenshuxian
	 * @createDate 2019年7月9日
	 */
	@RequestMapping("/getTopTenProduct")
	@ResponseBody
	public Map<String, Object> getTopTenProduct(HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		BigInteger shopId = new BigInteger(session.getAttribute("shopId") + "");
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
	 * 
	 * @param model
	 * @param page
	 * @param limit
	 * @param orderVo
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/appdylistOrderByOption")
	@ResponseBody
	public Map<String, Object> appdylistOrderByOption(Model model, Integer page, Integer limit, OrderVo orderVo,
			BigInteger shopId,String number) {
		// BigInteger shopId = (BigInteger) session.getAttribute("shopId");

		orderVo.setPage((page - 1) * page);//计算起始页数
		orderVo.setLimit(limit);//每页的条数
		orderVo.setShopId(shopId);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Order> list = orderService.listOrderByOption(orderVo);
		OrderProductVo orderProductVo = new OrderProductVo();
		orderProductVo.setOrderNumber(list.get(0).getNumber());
		orderProductVo.setPage((1 - 1) * 20);
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

	/**
	 * 商品的购买
	 * 
	 * @throws Exception
	 */

	@RequestMapping(value = "/buyshuopping", produces = "application/json; charset=utf-8")

	@ResponseBody
	public String buyshuopping(String barCode,int type, String authCode, String totalAmount,HttpSession session,int is_vip,String remark,String number) throws Exception {
		BigInteger shopId = (BigInteger) session.getAttribute("shopId");
		 
		String orderNumber=codnoutil.cood(shopId);

		
		if (type == 1) {
             
			int fig = orderService.updatetotalMoney(1, barCode, totalAmount,"",shopId,remark,is_vip,number);
			if (fig > 0) {
				
				return JsonUtil.getResponseJson(1, "支付成功", null, null);

			} else {
				return JsonUtil.getResponseJson(1, "支付成功", null, null);

			}
		} 
		
		else if (type == 4) {
			 List<GoodsDetail> goodsDetailList = orderService.goodsDetailList(barCode, shopId);
		       
			// alipaySweepPaymentcontroller alipaySweepPaymentcontroller=new
			// alipaySweepPaymentcontroller();

			AlipayF2FPayResult result = alipaySweepPaymentcontroller.test_trade_pay(orderNumber, "小欧商城", totalAmount,
					authCode, goodsDetailList);
			     //orderService.severAlipayF2FPayResult(result);
			switch (result.getTradeStatus()) {
			case SUCCESS:
				int fig = orderService.updatetotalMoney(3, barCode, totalAmount,result.getResponse().getOutTradeNo(),shopId,remark,is_vip,number);
				//orderService.updatetotalMoney(3, orderNumber, result.getResponse().getOutTradeNo());
				// fig=orderService.Increasecumulativeconsumptio(orderNumber,new
				// BigDecimal(totalAmount));
				log.info("支付宝支付成功: )");
				return JsonUtil.getResponseJson(1, "支付宝支付成功", null, null);

			case FAILED:
				log.error("支付宝支付失败!!!");
				return JsonUtil.getResponseJson(-1, result.getResponse().getSubMsg(), null, null);

			case UNKNOWN:
				log.error("系统异常，订单状态未知!!!");
				return JsonUtil.getResponseJson(-1, "系统异常，订单状态未知!!!", null, null);

			default:
				log.error("不支持的交易状态，交易返回异常!!!");
				return JsonUtil.getResponseJson(-1, "不支持的交易状态，交易返回异常!!!", null, null);
			}

		} else if (type == 3) {
			WechatPayConstants WechatPayConstants = new WechatPayConstants();

			Map<String, String> result = WechatPayConstants.micropay(totalAmount, authCode, orderNumber);
			
			String result_code = result.get("result_code");
             
			if (!"SUCCESS".equals(result_code)) {
				// 支付失败

				System.out.println("支付失败>>" + result);
				return JsonUtil.getResponseJson(-1, result.get("err_code_des"), null, null);

			}
			int fig = orderService.updatetotalMoney(4, barCode, totalAmount,result.get("out_trade_no"),shopId,remark,is_vip,number);
			// 支付成功
			
			// orderService.Increasecumulativeconsumptio(orderNumber, new
			// BigDecimal(total_fee));
			System.out.println("支付成功>>" + result);
			if (result.get("trade_state_desc") != null) {

				return JsonUtil.getResponseJson(1, result.get("trade_state_desc"), null, null);
			}
			return JsonUtil.getResponseJson(1, "支付成功", null, null);
		} else {
			int fig = orderService.updatetotalMoney(2, barCode, totalAmount,"",shopId,remark,is_vip,number);
			if (fig > 0) {
				return JsonUtil.getResponseJson(2, "支付成功", null, null);

			} else {
				return JsonUtil.getResponseJson(1, "支付成功", null, null);

			}
		}

	}
	

}
