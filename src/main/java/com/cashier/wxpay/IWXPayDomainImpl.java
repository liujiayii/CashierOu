package com.cashier.wxpay;
/**
 * @author 
 * @return
 * @author
 */
/**
 *
 * @ClassName: IWXPayDomainImpl

 * @description 
 *
 * @author 
 * @createDate 2018年11月27日
 */

public class IWXPayDomainImpl implements IWXPayDomain {

    /* (non-Javadoc)
     * @see com.cashier.wxpay.IWXPayDomain#report(java.lang.String, long, java.lang.Exception)
     */
    @Override
    public void report(String domain, long elapsedTimeMillis, Exception ex) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.cashier.wxpay.IWXPayDomain#getDomain(com.cashier.wxpay.WXPayConfig)
     */
    @Override
    public DomainInfo getDomain(WXPayConfig config) {
        DomainInfo domainInfo = new DomainInfo("api.mch.weixin.qq.com", true);
        return domainInfo;
    }
    
    
    public static DomainInfo Instance() {
        DomainInfo domainInfo = new DomainInfo("", true);
        return domainInfo;
    }

}
