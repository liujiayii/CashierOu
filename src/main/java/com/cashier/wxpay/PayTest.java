package com.cashier.wxpay;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 
 * @return
 * @author
 */
/**
 *
 * @ClassName: PayTest

 * @description 
 *
 * @author 
 * @createDate 2018年11月26日
 */

public class PayTest {
    
    
    public static void main(String[] args) throws Exception {
        WXPayConfig myConfig = new MyConfig();
        WXPay wxPay = new WXPay(myConfig,null,true, true);
        
        Map<String, String> reqData =new HashMap<>();
        reqData.put("body", "haha");
        reqData.put("out_trade_no", "201811261638");
        reqData.put("total_fee", "1");
        reqData.put("body", "haha");
       //String ip = InetAddress.getLocalHost().getHostAddress();  
        reqData.put("spbill_create_ip", "192.168.1.109");
        reqData.put("auth_code", "");
        
        
        reqData = wxPay.fillRequestData(reqData);
        
        String urlSuffix = "//api.mch.weixin.qq.com/sandboxnew/pay/micropay";
        reqData.put("trade_type", "JSAPI"); // 固定填写
        String result = wxPay.requestWithoutCert(urlSuffix, reqData, 5000, 5000);
        
        Map<String, String> resultMap = wxPay.processResponseXml(result);
        
        System.out.println(resultMap);
    }
}
