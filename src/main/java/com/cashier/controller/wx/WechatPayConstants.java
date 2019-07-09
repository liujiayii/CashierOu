package com.cashier.controller.wx;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.util.pay.util.HttpUtil;
import com.cashier.wxpay.WXPayConstants.SignType;





public class WechatPayConstants{
    @RequestMapping(value ="/micropay" ,produces = "application/json; charset=utf-8")
    @ResponseBody
	public String micropay(String total_fee,String auth_code,String out_trade_no) throws Exception{
		WXPayConfig WXPayConfig=new WXPayConfig();
		WXPay  WXPay=new WXPay(WXPayConfig);
		  Map<String, String> params = new HashMap<String, String>();
		  params.put("appid", WXPayConfig.AppID);
		  params.put("mch_id", WXPayConfig.MchID);
		  params.put("device_info", "javen205");//终端设备号
		  params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		  params.put("body", "刷卡支付测试");
		//  params.put("detail", "json字符串");//非必须
		  params.put("attach", "javen205");//附加参数非必须
		 // String out_trade_no=System.currentTimeMillis()+"";
		  params.put("out_trade_no", out_trade_no);
		  params.put("total_fee", total_fee);
		  
		 // String ip = IpKit.getRealIp(getRequest());
		 // if (StrKit.isBlank(ip)) {
		  String ip = "127.0.0.1";
		  //}
		  
		  params.put("spbill_create_ip", ip);
		  params.put("auth_code", auth_code);
		  
		  String sign = WXPayUtil.generateSignature(params, WXPayConfig.Key,SignType.MD5);
		  params.put("sign", sign);
		  Map<String, String> result = WXPay.microPay(params);
		  String return_code = result.get("return_code");
		  System.out.println("支付成功>>"+result);
		if (!"SUCCESS".equals(return_code)) {
		   //通讯失败 
		   String err_code = result.get("err_code");
		   //用户支付中，需要输入密码
		   if (err_code.equals("USERPAYING")) {
			   Thread.sleep(1000);
			   
			   Map<String, String> param = new HashMap<String, String>();
				  param.put("appid", WXPayConfig.AppID);
				  param.put("mch_id", WXPayConfig.MchID);
				  param.put("out_trade_no", out_trade_no);
				  param.put("nonce_str", System.currentTimeMillis() / 1000 + "");
				  String sig = WXPayUtil.generateSignature(param, WXPayConfig.Key,SignType.MD5);
				  param.put("sign", sig);
				   result = WXPay.orderQuery(param);
		   }
		
		   return " ";
		  }
		Thread.sleep(10000);//休眠等待
	   /**
	    * 查询订单的方法
	    */
		   Map<String, String> param = new HashMap<String, String>();
			  param.put("appid", WXPayConfig.AppID);
			  param.put("mch_id", WXPayConfig.MchID);
			  param.put("out_trade_no", out_trade_no);
			  param.put("nonce_str", System.currentTimeMillis() / 1000 + "");
			  String sig = WXPayUtil.generateSignature(param, WXPayConfig.Key,SignType.MD5);
			  param.put("sign", sig);
			  result = WXPay.orderQuery(param);
		  String result_code = result.get("result_code");
		if (!"SUCCESS".equals(result_code)) {
		   //支付失败

			System.out.println("支付失败>>"+result);
		   return  "支付失败";
		  }
		  
		  //支付成功 
		  
		System.out.println("支付成功>>"+result);
		return  "支付成功";
		 }

/**
 * 退款的方法
 * @throws Exception 
 */
    @RequestMapping(value ="/drawback" ,produces = "application/json; charset=utf-8")
    @ResponseBody
public  String   drawback() throws Exception	{
	WXPayConfig WXPayConfig=new WXPayConfig();
	WXPay  WXPay=new WXPay(WXPayConfig);
	 Map<String, String> params = new HashMap<String, String>();
	 params.put("appid", WXPayConfig.AppID);
	 params.put("mch_id", WXPayConfig.MchID);
	 params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
	  String sign = WXPayUtil.generateSignature(params, WXPayConfig.Key,SignType.MD5);
	  params.put("sign", sign);
	  params.put("transaction_id", "4200000335201907087900969300");
	  String out_trade_no=System.currentTimeMillis()+"";
	  params.put("out_refund_no", out_trade_no);
	  params.put("total_fee", "1");
	  params.put("refund_fee", "1");
	  System.out.println(params);
	  Map<String, String> refund= WXPay.refund(params);
	  System.out.println("退款接口"+refund);
	
	  	
	  String return_code = refund.get("return_code");
		   Map<String, String> param = new HashMap<String, String>();
			  param.put("appid", WXPayConfig.AppID);
			  param.put("mch_id", WXPayConfig.MchID);
			  param.put("out_trade_no", out_trade_no);
			  param.put("nonce_str", System.currentTimeMillis() / 1000 + "");
			  String sig = WXPayUtil.generateSignature(param, WXPayConfig.Key,SignType.MD5);
			  param.put("sign", sig);
			  
			  Map<String, String> refundQuery= WXPay.refundQuery(params);
			   return_code = refundQuery.get("return_code");
		if (/* StrKit.isBlank(result_code) || */ !"SUCCESS".equals(return_code)) {
		   //支付失败
			System.out.println("通讯失败>>"+refundQuery);
		   return  "";
		  }
		  
		  //支付成功 
		  
		System.out.println("退款查询接口"+refundQuery);
	
	return "支付成功";
	  
}
public static void main(String[] args) {
	WechatPayConstants  WechatPayConstants =new WechatPayConstants();
	try {
		String out_trade_no=System.currentTimeMillis()+"";
		//WechatPayConstants.micropay("1", "134842581208703442", out_trade_no);
        WechatPayConstants.drawback();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}
