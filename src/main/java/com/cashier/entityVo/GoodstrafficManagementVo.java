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

/**
 * @ClassName: GoodstrafficManagementVo
 * @description 货流表的vo类
 * @author liujunkai
 * @createDate 2018年11月19日
 */
public class GoodstrafficManagementVo implements Serializable{
    /** 序列号 */
    private static final long serialVersionUID = 1L;
    /** 订单流水号 */
    private String serialNumber ;
    /** 货流订单表ID */
    private int id ;
    /** 总金额 */
    private BigDecimal totalMoney ;
    /** 订单日期 */
    private String orderDate ;
    /** 送货日期 */
    private String deliveryDate ;
    /** 货流类型 */
    private Integer goodstrafficState ;
    /** 送货店名称 */
    private String receivingShopName;
    /** 申请调拨店名称 */
    private String shipmentsShopName;
    /** 运输状态 */
    private Integer transportationState;
    /** 备注 */
    private String remark ;
    /** 店铺名称 */
    private String name;
    /** 查询条数 */
    private int count;
    /** 页数 */
    private int page;   
    /** 行数 */
    private int limit;
    /** 申购结算状态 */
    private Integer settlementStatus;
    
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public Integer getGoodstrafficState() {
        return goodstrafficState;
    }
    public void setGoodstrafficState(Integer goodstrafficState) {
        this.goodstrafficState = goodstrafficState;
    }
   
    public Integer getTransportationState() {
        return transportationState;
    }
    public void setTransportationState(Integer transportationState) {
        this.transportationState = transportationState;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
  
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public Integer getSettlementStatus() {
        return settlementStatus;
    }
    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
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
    
    
    
    
    @Override
    public String toString() {
        return "GoodstrafficManagementVo [serialNumber=" + serialNumber + ", id=" + id + ", totalMoney=" + totalMoney
                + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", goodstrafficState="
                + goodstrafficState + ", receivingShopName=" + receivingShopName + ", shipmentsShopName="
                + shipmentsShopName + ", transportationState=" + transportationState + ", remark=" + remark + ", name="
                + name + ", count=" + count + ", page=" + page + ", limit=" + limit + ", settlementStatus="
                + settlementStatus + "]";
    }
    public GoodstrafficManagementVo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
}
