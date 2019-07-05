package com.cashier.wxpay;

import java.io.*;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Random;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author 
 * @return
 * @author
 */
/**
 *
 * @ClassName: MyConfig

 * @description 
 *
 * @author 
 * @createDate 2018年11月26日
 */

public final class MyConfig extends WXPayConfig {
    
    private byte[] certData;



    public MyConfig() throws Exception {
        //API证书       
        //不是沙箱环境要要下载证书，开出来
        String basePath = this.getClass().getClassLoader().getResource("/").getPath();
        String certPath = basePath + "apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    
    }
        
        /*KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File("D:/apiclient_cert.p12"));//放退款证书的路径
        try {
             keyStore.load(instream, "1514947161".toCharArray());
        } finally {
             instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, "1514947161".toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
            sslcontext,
            new String[] { "TLSv1" },
            null,
        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
*/
    @Override
    String getAppID() {
        // TODO Auto-generated method stub
        return "wx0c3cfdd3f02bd3bb";
    }
    @Override
    String getMchID() {
        // TODO Auto-generated method stub
        return "1514947161";
    }
    @Override
    String getKey() {
        // TODO Auto-generated method stub
        return "D2883F7B896D1EAC93945C12BD7D046F";
    }
    @Override
    InputStream getCertStream() {
        // TODO Auto-generated method stub
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        
        return certBis;
    }
    @Override
    IWXPayDomain getWXPayDomain() {
        // TODO Auto-generated method stub
        return WXPayDomainSimpleImpl.instance();
    }
    @Override
    public String toString() {
        return "MyConfig [getAppID()=" + getAppID() + ", getMchID()=" + getMchID() + ", getKey()=" + getKey()
                + ", getCertStream()=" + getCertStream() + ", getWXPayDomain()=" + getWXPayDomain()
                + ", getHttpConnectTimeoutMs()=" + getHttpConnectTimeoutMs() + ", getHttpReadTimeoutMs()="
                + getHttpReadTimeoutMs() + ", shouldAutoReport()=" + shouldAutoReport() + ", getReportWorkerNum()="
                + getReportWorkerNum() + ", getReportQueueMaxSize()=" + getReportQueueMaxSize()
                + ", getReportBatchSize()=" + getReportBatchSize() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }
    
    

}
