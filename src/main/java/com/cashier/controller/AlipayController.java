/**
 * @Title: AlipayController.java
 * @Package com.cashier.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Administrator
 * @date 2019年3月12日
 */
package com.cashier.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.cashier.entity.Alipay;
import com.cashier.util.AlipayConfig;
import com.cashier.util.logFile;

/**
 * @ClassName: AlipayController
 * @description 用一句话描述这个类的作用
 * @author 姓名全拼
 * @createDate 2019年3月12日
 */
@Controller
public class AlipayController {
    
   
    //@RequiresPermissions("")
    @RequestMapping(value = "/pay")
    @ResponseBody
    public ModelAndView pay(String totalAmount) {
        ModelAndView mv = new ModelAndView();
        Alipay alipay = new Alipay();
        alipay.setTotalAmount(totalAmount);
        mv.addObject("alipay", alipay);
        mv.setViewName("/views/wappayManage/wappay/pay");
        
        return mv;
    }
    
   /*
    *  1、支付宝充值，或者任务支付
    * @author yangxujia
    * @throws UnsupportedEncodingException 
    * @date 2015年10月28日上午11:44:07
    */ 
   @RequestMapping(value = "/receiveFromAlipay")
   @ResponseBody
   public String receiveFromAlipay(HttpServletRequest request) throws UnsupportedEncodingException{
       //获取支付宝POST过来反馈信息
       Map<String,String> params = new HashMap<String,String>();
       Map requestParams = request.getParameterMap();
       //logFile.logResult("requestParams:"+requestParams.toString());
       //logFile.logResult("params:"+params.toString());
     //  for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
          /// String name = (String) iter.next();
          // String[] values = (String[]) requestParams.get(name);
          // String valueStr = "";
           //for (int i = 0; i < values.length; i++) {
              // valueStr = (i == values.length - 1) ? valueStr + values[i]
                //       : valueStr + values[i] + ",";
          // }
           //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
           //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
         //  params.put(name, valueStr);
       //}
       
       //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
       //商户订单号 String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

       //支付宝交易号    String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
       if(request.getParameter("trade_status")==null){
           logFile.logResult("fail");
           return "fail";
       }

       String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
       params.put("trade_status", trade_status);//add by yangxj
       
       boolean verify_result = false;
       try {
            verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

       if(verify_result){//验证成功
           //////////////////////////////////////////////////////////////////////////////////////////
           //请在这里加上商户的业务逻辑程序代码

           //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
           
           if(trade_status.equals("TRADE_FINISHED")){
               //判断该笔订单是否在商户网站中已经做过处理
                   //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                   //如果有做过处理，不执行商户的业务程序
                   
               //注意：
               //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
               //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
           } else if (trade_status.equals("TRADE_SUCCESS")){
               //判断该笔订单是否在商户网站中已经做过处理
                   //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                   //如果有做过处理，不执行商户的业务程序
               //注意：
               //付款完成后，支付宝系统发送该交易状态通知
               //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
           }
           //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
           return "success";
//         out.println("success"); //请不要修改或删除
           //////////////////////////////////////////////////////////////////////////////////////////
       }else{//验证失败
           return "fail";
//         out.println("fail");
       }
   }


}
