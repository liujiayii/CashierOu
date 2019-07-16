package com.cashier.util.pay.util;
/**
 *<P> 支付宝交易的接口</p>
 */
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.alipay.demo.trade.utils.Utils;
import com.cashier.entity.OrderProduct;
import com.cashier.service.OrderProductService;
import com.cashier.service.OrderService;
import com.cashier.util.pay.util.JsonUtil;


@Controller
public class alipaySweepPaymentcontroller {
	
	private static Log                  log = LogFactory.getLog(Main.class);
	@Autowired
	public OrderProductService orderProductService;

	@Autowired
	public OrderService orderService;
    // 支付宝当面付2.0服务
    private static AlipayTradeService   tradeService;

    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
    private static AlipayTradeService   tradeWithHBService;

    // 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
    private static AlipayMonitorService monitorService;
	private static alipaySweepPaymentcontroller logUtil;
	//重点三：初始化
    @PostConstruct 
public void init() {       
	 logUtil= this; 

} 
    static {
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

        /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
            .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
            .setFormat("json").build();
    }
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                    response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }

	 public void test_trade_query(String outTradeNo) {
	        // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
		

	        // 创建查询请求builder，设置请求参数
	        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
	            .setOutTradeNo(outTradeNo);

	        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
	        switch (result.getTradeStatus()) {
	            case SUCCESS:
	                log.info("查询返回该订单支付成功: )");

	                AlipayTradeQueryResponse response = result.getResponse();
	                dumpResponse(response);

	                log.info(response.getTradeStatus());
	                if (Utils.isListNotEmpty(response.getFundBillList())) {
	                    for (TradeFundBill bill : response.getFundBillList()) {
	                        log.info(bill.getFundChannel() + ":" + bill.getAmount());
	                    }
	                }
	                break;

	            case FAILED:
	                log.error("查询返回该订单支付失败或被关闭!!!");
	                break;

	            case UNKNOWN:
	                log.error("系统异常，订单支付状态未知!!!");
	                break;

	            default:
	                log.error("不支持的交易状态，交易返回异常!!!");
	                break;
	        }
	    }
	 

    /**
     * 支付接口
     * @param service
     */  
    
     public static AlipayF2FPayResult test_trade_pay(String orderNumber,String subject,String totalAmount,String authCode,List<GoodsDetail> goodsDetailList ) {
		      // (可选，根据需要决定是否使用) 订单可打折金额，可以配合商家平台配置折扣活动，如果订单部分商品参与打折，可以将部分商品总价填写至此字段，默认全部商品可打折
         // 如果该值未传入,但传入了【订单总金额】,【不可打折金额】 则该值默认为【订单总金额】- 【不可打折金额】
         //        String discountableAmount = "1.00"; //
         String outTradeNo =""+System.currentTimeMillis()
         + (long) (Math.random() * 10000000L);
         // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
         // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
         String undiscountableAmount = "0.0";

         // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
         // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
         String sellerId = "";

         // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品3件共20.00元"
         String body = "线下购买";

         // 商户操作员编号，添加此参数可以为商户操作员做销售统计
         String operatorId = "test_operator_id";

         // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
         String storeId = "test_store_id";

         // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
         String providerId = "2088100200300400500";
         ExtendParams extendParams = new ExtendParams();
         extendParams.setSysServiceProviderId(providerId);

         // 支付超时，线下扫码交易定义为5分钟
         String timeoutExpress = "5m";

         // 商品明细列表，需填写购买商品详细信息，
      
		/*
		 * // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
		 * GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx面包", 1000,
		 * 1); // 创建好一个商品后添加至商品明细列表 goodsDetailList.add(goods1);
		 * 
		 * // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件 GoodsDetail goods2 =
		 * GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
		 */
        
         String appAuthToken = "应用授权令牌";//根据真实值填写

         // 创建条码支付请求builder，设置请求参数
         AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
             //            .setAppAuthToken(appAuthToken)
             .setOutTradeNo(outTradeNo).setSubject(subject).setAuthCode(authCode)
             .setTotalAmount(totalAmount).setStoreId(storeId)
             .setUndiscountableAmount(undiscountableAmount).setBody(body).setOperatorId(operatorId)
             .setExtendParams(extendParams).setSellerId(sellerId)
             .setGoodsDetailList(goodsDetailList).setTimeoutExpress(timeoutExpress);

         // 调用tradePay方法获取当面付应答
         AlipayTradeService service=tradeService;
         
         AlipayF2FPayResult result = service.tradePay(builder);
         System.out.println("tradeService的值"+result.getResponse().getMsg());
         
          return  result;
		
     }
 
     public static AlipayF2FRefundResult  test_trade_refund(String outTradeNo,String refundAmount) {
		
         // 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
         String outRequestNo = "";

         // (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
         String refundReason = "正常退款，用户买多了";

         // (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
         String storeId = "test_store_id";

         // 创建退款请求builder，设置请求参数
         AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
             .setOutTradeNo(outTradeNo).setRefundAmount(refundAmount).setRefundReason(refundReason)
             .setOutRequestNo(outRequestNo).setStoreId(storeId);
        // System.out.println("tradeService的值"+result.getResponse().getSubMsg());
         AlipayF2FRefundResult result = tradeService.tradeRefund(builder);
         System.out.println("tradeService的值"+result.getResponse().getSubMsg());
         
         return  result;
     }
   
}
