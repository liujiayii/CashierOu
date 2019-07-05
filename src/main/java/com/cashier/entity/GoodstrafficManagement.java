package com.cashier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class GoodstrafficManagement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger id;
	// 发起调拨店铺ID
	private BigInteger shipmentsShopId;
	// 送货店铺ID
	private BigInteger receivingShopId;
	// 总金额
	private BigDecimal totalMoney;
	/** 订单日期 */
	private Timestamp orderDate;
	/** 送货日期 */
	private Timestamp deliveryDate;
	/** 货流类型 1:采购 2:调拨  */
	private Integer goodstrafficState;

	/** 运输状态 1:未审批 2:备货中 3:已入库 4:已拒绝5:已入库 */
	private Integer transportationState;
	/** 备注 */
	private String remark;
	/** 店铺ID */
	private BigInteger shopId;
	/** 流水号 */
	private String serialnumber;
	/** 结算状态0为未结算/1为已结算 */
	private Integer settlementStatus;
	/** 页数 */
	private int page;
	/** 行数 */
	private int limit;

	public GoodstrafficManagement() {
		super();
	}

	public GoodstrafficManagement(BigInteger id, BigInteger shipmentsShopId, BigInteger receivingShopId,
			BigDecimal totalMoney, Timestamp orderDate, Timestamp deliveryDate, Integer goodstrafficState,
			Integer productState, Integer transportationState, String remark, BigInteger shopId, String serialnumber,
			Integer settlementStatus, int page, int limit) {
		super();
		this.id = id;
		this.shipmentsShopId = shipmentsShopId;
		this.receivingShopId = receivingShopId;
		this.totalMoney = totalMoney;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.goodstrafficState = goodstrafficState;

		this.transportationState = transportationState;
		this.remark = remark;
		this.shopId = shopId;
		this.serialnumber = serialnumber;
		this.settlementStatus = settlementStatus;
		this.page = page;
		this.limit = limit;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getShipmentsShopId() {
		return shipmentsShopId;
	}

	public void setShipmentsShopId(BigInteger shipmentsShopId) {
		this.shipmentsShopId = shipmentsShopId;
	}

	public BigInteger getReceivingShopId() {
		return receivingShopId;
	}

	public void setReceivingShopId(BigInteger receivingShopId) {
		this.receivingShopId = receivingShopId;
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

	public BigInteger getShopId() {
		return shopId;
	}

	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public Integer getSettlementStatus() {
		return settlementStatus;
	}

	public void setSettlementStatus(Integer settlementStatus) {
		this.settlementStatus = settlementStatus;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((goodstrafficState == null) ? 0 : goodstrafficState.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + limit;
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + page;
		result = prime * result + ((receivingShopId == null) ? 0 : receivingShopId.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((serialnumber == null) ? 0 : serialnumber.hashCode());
		result = prime * result + ((settlementStatus == null) ? 0 : settlementStatus.hashCode());
		result = prime * result + ((shipmentsShopId == null) ? 0 : shipmentsShopId.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
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
		GoodstrafficManagement other = (GoodstrafficManagement) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (limit != other.limit)
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (page != other.page)
			return false;
		if (receivingShopId == null) {
			if (other.receivingShopId != null)
				return false;
		} else if (!receivingShopId.equals(other.receivingShopId))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (serialnumber == null) {
			if (other.serialnumber != null)
				return false;
		} else if (!serialnumber.equals(other.serialnumber))
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
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
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

	@Override
	public String toString() {
		return "GoodstrafficManagement [id=" + id + ", shipmentsShopId=" + shipmentsShopId + ", receivingShopId="
				+ receivingShopId + ", totalMoney=" + totalMoney + ", orderDate=" + orderDate + ", deliveryDate="
				+ deliveryDate + ", goodstrafficState=" + goodstrafficState + ", transportationState="
				+ transportationState + ", remark=" + remark + ", shopId=" + shopId + ", serialnumber=" + serialnumber
				+ ", settlementStatus=" + settlementStatus + ", page=" + page + ", limit=" + limit + "]";
	}

}
