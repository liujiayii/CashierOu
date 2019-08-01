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
    /** 送货店ID */
    private BigInteger shipmentsShopId;
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
    /**
     * 
     */
    public GoodstrafficManagementVo() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @param serialNumber
     * @param id
     * @param totalMoney
     * @param orderDate
     * @param deliveryDate
     * @param goodstrafficState
     * @param shipmentsShopId
     * @param receivingShopName
     * @param shipmentsShopName
     * @param transportationState
     * @param remark
     * @param name
     * @param count
     * @param page
     * @param limit
     * @param settlementStatus
     */
    public GoodstrafficManagementVo(String serialNumber, int id, BigDecimal totalMoney, String orderDate,
            String deliveryDate, Integer goodstrafficState, BigInteger shipmentsShopId, String receivingShopName,
            String shipmentsShopName, Integer transportationState, String remark, String name, int count, int page,
            int limit, Integer settlementStatus) {
        super();
        this.serialNumber = serialNumber;
        this.id = id;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.goodstrafficState = goodstrafficState;
        this.shipmentsShopId = shipmentsShopId;
        this.receivingShopName = receivingShopName;
        this.shipmentsShopName = shipmentsShopName;
        this.transportationState = transportationState;
        this.remark = remark;
        this.name = name;
        this.count = count;
        this.page = page;
        this.limit = limit;
        this.settlementStatus = settlementStatus;
    }
    @Override
    public String toString() {
        return "GoodstrafficManagementVo [serialNumber=" + serialNumber + ", id=" + id + ", totalMoney=" + totalMoney
                + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", goodstrafficState="
                + goodstrafficState + ", shipmentsShopId=" + shipmentsShopId + ", receivingShopName="
                + receivingShopName + ", shipmentsShopName=" + shipmentsShopName + ", transportationState="
                + transportationState + ", remark=" + remark + ", name=" + name + ", count=" + count + ", page=" + page
                + ", limit=" + limit + ", settlementStatus=" + settlementStatus + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + count;
        result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
        result = prime * result + ((goodstrafficState == null) ? 0 : goodstrafficState.hashCode());
        result = prime * result + id;
        result = prime * result + limit;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
        result = prime * result + page;
        result = prime * result + ((receivingShopName == null) ? 0 : receivingShopName.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
        result = prime * result + ((settlementStatus == null) ? 0 : settlementStatus.hashCode());
        result = prime * result + ((shipmentsShopId == null) ? 0 : shipmentsShopId.hashCode());
        result = prime * result + ((shipmentsShopName == null) ? 0 : shipmentsShopName.hashCode());
        result = prime * result + ((totalMoney == null) ? 0 : totalMoney.hashCode());
        result = prime * result + ((transportationState == null) ? 0 : transportationState.hashCode());
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
        GoodstrafficManagementVo other = (GoodstrafficManagementVo) obj;
        if (count != other.count)
            return false;
        if (deliveryDate == null) {
            if (other.deliveryDate != null)
                return false;
        } else if (!deliveryDate.equals(other.deliveryDate))
            return false;
        if (goodstrafficState == null) {
            if (other.goodstrafficState != null)
                return false;
        } else if (!goodstrafficState.equals(other.goodstrafficState))
            return false;
        if (id != other.id)
            return false;
        if (limit != other.limit)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (orderDate == null) {
            if (other.orderDate != null)
                return false;
        } else if (!orderDate.equals(other.orderDate))
            return false;
        if (page != other.page)
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
        if (settlementStatus == null) {
            if (other.settlementStatus != null)
                return false;
        } else if (!settlementStatus.equals(other.settlementStatus))
            return false;
        if (shipmentsShopId == null) {
            if (other.shipmentsShopId != null)
                return false;
        } else if (!shipmentsShopId.equals(other.shipmentsShopId))
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
        if (transportationState == null) {
            if (other.transportationState != null)
                return false;
        } else if (!transportationState.equals(other.transportationState))
            return false;
        return true;
    }
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
    public BigInteger getShipmentsShopId() {
        return shipmentsShopId;
    }
    public void setShipmentsShopId(BigInteger shipmentsShopId) {
        this.shipmentsShopId = shipmentsShopId;
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
    
    
    
}
