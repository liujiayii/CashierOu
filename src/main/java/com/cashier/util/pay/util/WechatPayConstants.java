package com.cashier.util.pay.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.entity.Order;
import com.cashier.entity.OrderProduct;
import com.cashier.service.OrderProductService;
import com.cashier.service.OrderService;

import com.cashier.util.pay.util.JsonUtil;
import com.cashier.wxpay.WXPayConstants.SignType;

@Controller
public class WechatPayConstants {
	@Resource
	private OrderService orderService;
	@Resource
	private OrderProductService orderProductService;
      /***
       * 微信支付
       * @param total_fee
       * @param auth_code
       * @param orderNumber
       * @return
       * @throws Exception
       */
	private static WechatPayConstants logUtil;
	//重点三：初始化
    @PostConstruct 
public void init() {       
	 logUtil= this; 

} 
	public Map<String, String> micropay(String total_fee, String auth_code, String orderNumber) throws Exception {
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


		return result;
		
	}

	/**
	 * 退款的方法
	 * 
	 * @throws Exception
	 */
	
	public Map<String, String> drawback(String out_trade_no, String total_fee, String refund_fee) throws Exception {
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
		return  refundQuery;
	}
				
}
