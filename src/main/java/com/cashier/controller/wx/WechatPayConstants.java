package com.cashier.controller.wx;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.entity.Order;
import com.cashier.entity.OrderProduct;
import com.cashier.service.OrderProductService;
import com.cashier.service.OrderService;
import com.cashier.util.pay.util.HttpUtil;
import com.cashier.util.pay.util.JsonUtil;
import com.cashier.wxpay.WXPayConstants.SignType;

@Controller
public class WechatPayConstants {
	@Resource
	private OrderService orderService;
	@Resource
	private OrderProductService orderProductService;

	@RequestMapping(value = "/micropay", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String micropay(String total_fee, String auth_code, String orderNumber) throws Exception {
		WXPayConfig WXPayConfig = new WXPayConfig();
		WXPay WXPay = new WXPay(WXPayConfig);
		Map<String, String> params = new HashMap<String, String>();
		params.put("device_info", "javen205");// 终端设备号
		params.put("body", "小欧商城");
		// params.put("detail", "json字符串");//非必须
		params.put("attach", "javen205");// 附加参数非必须
		String out_trade_no = System.currentTimeMillis() + "";
		params.put("out_trade_no", out_trade_no);
		params.put("total_fee", total_fee);

		// String ip = IpKit.getRealIp(getRequest());
		// if (StrKit.isBlank(ip)) {
		//String ip = "127.0.0.1";
		// }

		//params.put("spbill_create_ip", ip);
		params.put("auth_code", auth_code);
		Map<String, String> result = WXPay.microPay(params);
		String result_code = result.get("result_code");
		System.out.println("支付成功>>" + result);
		if (!"SUCCESS".equals(result_code)) {
			// 通讯失败
			String err_code = result.get("err_code");
			// 用户支付中，需要输入密码
			if (err_code.equals("USERPAYING")) {
				Thread.sleep(10000);// 休眠等待
				Map<String, String> param = new HashMap<String, String>();
				param.put("out_trade_no", out_trade_no);
				result = WXPay.orderQuery(param);
			}

			
		}


		
		result_code = result.get("result_code");

		if (!"SUCCESS".equals(result_code)) {
			// 支付失败

			System.out.println("支付失败>>" + result);
			return JsonUtil.getResponseJson(-1, result.get("err_code_des"), null, null);

		}
	
		// 支付成功
		orderService.updatetotalMoney(4, orderNumber,out_trade_no);
		//orderService.Increasecumulativeconsumptio(orderNumber, new BigDecimal(total_fee));
		System.out.println("支付成功>>" + result);
		if (result.get("trade_state_desc") != null) {

			return JsonUtil.getResponseJson(1, result.get("trade_state_desc"), null, null);
		}
		return JsonUtil.getResponseJson(1, "支付成功", null, null);
	}

	/**
	 * 退款的方法
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/drawback", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String drawback(String out_trade_no, String total_fee, String refund_fee) throws Exception {
		WXPayConfig WXPayConfig = new WXPayConfig();
		WXPay WXPay = new WXPay(WXPayConfig);
		Map<String, String> params = new HashMap<String, String>();
		params.put("out_trade_no", out_trade_no);
		String out_refund_no = System.currentTimeMillis() + "";
		params.put("out_refund_no", out_refund_no);
		params.put("total_fee", total_fee);
		params.put("refund_fee", refund_fee);
		System.out.println(params);
		Map<String, String> refund = WXPay.refund(params);
		System.out.println("退款接口" + refund);

		String return_code = refund.get("return_code");
		Map<String, String> param = new HashMap<String, String>();

		param.put("out_trade_no", out_trade_no);
		param.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		Map<String, String> refundQuery = WXPay.refundQuery(params);
		return_code = refundQuery.get("result_code");
		if (!"SUCCESS".equals(return_code)) {
			// 支付失败
			System.out.println("通讯失败>>" + refundQuery);
			return JsonUtil.getResponseJson(-1, refund.get("err_code_des"), null, null);
		}

		// 支付成功

		orderService.updateOrderStates(out_trade_no, total_fee);
		System.out.println("退款查询接口" + refundQuery);

		return JsonUtil.getResponseJson(1, "微信退款成功", null, null);

	}

}
