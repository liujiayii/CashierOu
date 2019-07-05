/**
 * @Title: Alipay.java
 * @Package com.cashier.entity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Administrator
 * @date 2019年3月12日
 */
package com.cashier.entity;

import java.io.Serializable;

/**
 * @ClassName: Alipay
 * @description 用一句话描述这个类的作用
 * @author 姓名全拼
 * @createDate 2019年3月12日
 */
public class Alipay implements Serializable{
    
    /**  字段的含义 */
    private static final long serialVersionUID = 1L;
    /**  总金额 */
    String totalAmount;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Alipay [totalAmount=" + totalAmount + "]";
    }

    public Alipay(String totalAmount) {
        super();
        this.totalAmount = totalAmount;
    }

    public Alipay() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
}
