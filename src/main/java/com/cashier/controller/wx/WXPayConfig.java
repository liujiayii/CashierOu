package com.cashier.controller.wx;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public  class WXPayConfig {
	  



	  

	  



	   

	   //不是沙箱环境要要下载证书，开出来

	    

	   
	   


    /**
     * 获取 App ID
     *
     * @return App ID
     */
    public static final String AppID="wxb24ea56f10dec071";


    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    public static final String MchID="1538075201";


    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    public static final String Key="7a4w5mAhHkr5HDqNdfO03xpEs6brqwKw";
    
    /**
     付款的url
     *
     * @return API密钥
     */
    public static final String url="https://api.mch.weixin.qq.com/pay/micropay";
    /**
    查询订单的urls
    *
    * @return API密钥
    */
   public static final String urls="https://api.mch.weixin.qq.com/pay/orderquery";
  /* 退款订单的urls
   *
   * @return API密钥
   */
  public static final String tkcxurls="https://api.mch.weixin.qq.com/pay/refundquery";

  /* 退款查询订单的urls
  *
  * @return API密钥
  */
 public static final String tkurls="https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     * @throws IOException 
     */

    public static InputStream getCertStream() throws IOException {
    	   byte[] certData;
    	   String certPath = "D:\\wxb24ea56f10dec071.p12";

	       File file = new File(certPath);

	       InputStream certStream = new FileInputStream(file);

	       certData = new byte[(int) file.length()];

	       certStream.read(certData);
	       certStream.close();
	       
    	 ByteArrayInputStream certBis = new ByteArrayInputStream(certData);
    	 
         return certBis;
		
        
    }

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpConnectTimeoutMs() {
        return 6*1000;
    }

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    public int getHttpReadTimeoutMs() {
        return 8*1000;
    }

    /**
     * 获取WXPayDomain, 用于多域名容灾自动切换
     * @return
     */
    public static IWXPayDomain getWXPayDomain() {
    	IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    
    	
    }

    /**
     * 是否自动上报。
     * 若要关闭自动上报，子类中实现该函数返回 false 即可。
     *
     * @return
     */
    public boolean shouldAutoReport() {
        return true;
    }

    /**
     * 进行健康上报的线程的数量
     *
     * @return
     */
    public int getReportWorkerNum() {
        return 6;
    }


    /**
     * 健康上报缓存消息的最大数量。会有线程去独立上报
     * 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
     *
     * @return
     */
    public int getReportQueueMaxSize() {
        return 10000;
    }

    /**
     * 批量上报，一次最多上报多个数据
     *
     * @return
     */
    public int getReportBatchSize() {
        return 10;
    }

}
