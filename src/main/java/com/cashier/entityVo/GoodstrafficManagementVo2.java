/**
 * @Title: GoodstrafficManagementVo.java
 * @Package com.cashier.entityVo
 * @Description: TODO(页面显示辅助类)
 * @author Administrator
 * @date 2018年11月19日
 */
package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName: GoodstrafficManagementVo
 * @description 货流表的vo类
 * @author liujunkai
 * @createDate 2018年11月19日
 */
public class GoodstrafficManagementVo2 implements Serializable {
    /** 序列号 */
    private static final long serialVersionUID = 1L;
    /** 订单流水号 */
    private String serialNumber;
    /** 货流订单表ID */
    private BigInteger id;
    /** 总金额 */
    private BigDecimal totalMoney;
    /** 订单日期 */
    private Timestamp orderDate;
    /** 送货日期 */
    private Timestamp deliveryDate;
    /** 送货店名称 */
    private String receivingShopName;
    /** 申请调拨店名称 */
    private String shipmentsShopName;
    /** 备注 */
    private String remark;
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getReceivingShopName() {
        return receivingShopName;
    }
    public void setReceivingShopName(String receivingShopName) {
        this.receivingShopName = receivingShopName;
    }
    public String getShipmentsShopName() {
        return shipmentsShopName;
    }
    public void setShipmentsShopName(String shipmentsShopName) {
        this.shipmentsShopName = shipmentsShopName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Override
    public String toString() {
        return "GoodstrafficManagementVo2 [serialNumber=" + serialNumber + ", id=" + id + ", totalMoney=" + totalMoney
                + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", receivingShopName="
                + receivingShopName + ", shipmentsShopName=" + shipmentsShopName + ", remark=" + remark + "]";
    }
    public GoodstrafficManagementVo2(String serialNumber, BigInteger id, BigDecimal totalMoney, Timestamp orderDate,
            Timestamp deliveryDate, String receivingShopName, String shipmentsShopName, String remark) {
        super();
        this.serialNumber = serialNumber;
        this.id = id;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.receivingShopName = receivingShopName;
        this.shipmentsShopName = shipmentsShopName;
        this.remark = remark;
    }
    public GoodstrafficManagementVo2() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
        result = prime * result + ((receivingShopName == null) ? 0 : receivingShopName.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
        result = prime * result + ((shipmentsShopName == null) ? 0 : shipmentsShopName.hashCode());
        result = prime * result + ((totalMoney == null) ? 0 : totalMoney.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GoodstrafficManagementVo2 other = (GoodstrafficManagementVo2) obj;
        if (deliveryDate == null) {
            if (other.deliveryDate != null)
                return false;
        } else if (!deliveryDate.equals(other.deliveryDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (orderDate == null) {
            if (other.orderDate != null)
                return false;
        } else if (!orderDate.equals(other.orderDate))
            return false;
        if (receivingShopName == null) {
            if (other.receivingShopName != null)
                return false;
        } else if (!receivingShopName.equals(other.receivingShopName))
            return false;
        if (remark == null) {
            if (other.remark != null)
                return false;
        } else if (!remark.equals(other.remark))
            return false;
        if (serialNumber == null) {
            if (other.serialNumber != null)
                return false;
        } else if (!serialNumber.equals(other.serialNumber))
            return false;
        if (shipmentsShopName == null) {
            if (other.shipmentsShopName != null)
                return false;
        } else if (!shipmentsShopName.equals(other.shipmentsShopName))
            return false;
        if (totalMoney == null) {
            if (other.totalMoney != null)
                return false;
        } else if (!totalMoney.equals(other.totalMoney))
            return false;
        return true;
    }
   
    
}
