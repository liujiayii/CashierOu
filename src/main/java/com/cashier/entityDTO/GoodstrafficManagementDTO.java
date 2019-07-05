/**
 * @Title: GoodstrafficManagementDTO.java
 * @Package com.cashier.entityDTO
 * @Description: TODO(页面输入数据辅助类)
 * @author Administrator
 * @date 2018年11月19日
 */
package com.cashier.entityDTO;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: GoodstrafficManagementDTO
 * @description 各种查询条件的输入数据
 * @author liujunkai
 * @createDate 2018年11月19日
 */
public class GoodstrafficManagementDTO implements Serializable{
    /** 序列号 */
    private static final long serialVersionUID = 1L;
    /** 运输状态 */
    private Integer transportationState;
    /** 店铺名称 */
    private String name;
    /** 筛选起始时间 */
    private String startdate;
    /** 筛选结束时间 */
    private String enddate;
    /** 页数 */
    private int page;   
    /** 行数 */
    private int limit;
    /** 店铺ID */
    private BigInteger shopId ;
    /** 订单总数 */
	private Integer sumOrder;
	/** 订单总金额 */
	private Integer sumMoney;
	
	
	public Integer getTransportationState() {
		return transportationState;
	}
	public void setTransportationState(Integer transportationState) {
		this.transportationState = transportationState;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
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
	public BigInteger getShopId() {
		return shopId;
	}
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}
	public Integer getSumOrder() {
		return sumOrder;
	}
	public void setSumOrder(Integer sumOrder) {
		this.sumOrder = sumOrder;
	}
	public Integer getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(Integer sumMoney) {
		this.sumMoney = sumMoney;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enddate == null) ? 0 : enddate.hashCode());
		result = prime * result + limit;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + page;
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((startdate == null) ? 0 : startdate.hashCode());
		result = prime * result + ((sumMoney == null) ? 0 : sumMoney.hashCode());
		result = prime * result + ((sumOrder == null) ? 0 : sumOrder.hashCode());
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
		GoodstrafficManagementDTO other = (GoodstrafficManagementDTO) obj;
		if (enddate == null) {
			if (other.enddate != null)
				return false;
		} else if (!enddate.equals(other.enddate))
			return false;
		if (limit != other.limit)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (page != other.page)
			return false;
		
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		if (startdate == null) {
			if (other.startdate != null)
				return false;
		} else if (!startdate.equals(other.startdate))
			return false;
		if (sumMoney == null) {
			if (other.sumMoney != null)
				return false;
		} else if (!sumMoney.equals(other.sumMoney))
			return false;
		if (sumOrder == null) {
			if (other.sumOrder != null)
				return false;
		} else if (!sumOrder.equals(other.sumOrder))
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
		return "GoodstrafficManagementDTO [transportationState="
				+ transportationState + ", name=" + name + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", page=" + page + ", limit=" + limit + ", shopId=" + shopId
				+ ", sumOrder=" + sumOrder + ", sumMoney=" + sumMoney + "]";
	}
    
}
