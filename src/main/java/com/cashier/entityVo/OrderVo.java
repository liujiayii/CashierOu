package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;


/**
 *
 * @ClassName: OrderVo
 * @description 订单表的Vo实体类
 *
 * @author dujiawei
 * @createDate 2019年6月21日
 */
public class OrderVo implements Serializable {
	
	/** UID */
	private static final long serialVersionUID = 1L;
	/** 消费订单id */
	private BigInteger id;
	/** 消费订单编号 */
	private String number;
	/** 会员卡号码  */
    private String memberNumber;
	/** 订单创建时间 */
	private Timestamp createTime;
	/** 店铺id */
	private BigInteger shopId;
	/** 店铺名  */
    private String shopName;
	/** 交易方式：1现金、2POS机、3支付宝、4微信支付 */
	private Integer payMethod;
	
	/** 开户行名称  */
    private String bankName;
    /** 银行卡号  */
    private String cardNumber;
	
	/**订单应收金额*/
	private BigDecimal payAdvance;
	/**手动输入折扣*/
	private BigDecimal customDiscount;
	/**实付总金额*/
	private BigDecimal totalMoney;
	/**订单状态（1出售、2退货）*/
	private Integer state;
	/**备注*/
	private String remark;
	/** 退货状态：
	 * 1.退货了 
	 * 2.没退货 */
	private Integer refund;
	
	/** 当前所在页 */
	private int page = 1;
	/** 每页显示条数 */
	private int limit = 5;
	/** 数据条数 */
	private Integer count;
	/** 订单总数 */
	private Integer sumOrder;
	/** 订单总金额 */
	private Integer sumOrderMoney;
	
	/** 开始时间 */
	private String startTime;
	/** 结束时间 */
	private String endTime;
	
	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the memberNumber
	 */
	public String getMemberNumber() {
		return memberNumber;
	}
	/**
	 * @param memberNumber the memberNumber to set
	 */
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the shopId
	 */
	public BigInteger getShopId() {
		return shopId;
	}
	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}
	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}
	/**
	 * @param shopName the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * @return the payMethod
	 */
	public Integer getPayMethod() {
		return payMethod;
	}
	/**
	 * @param payMethod the payMethod to set
	 */
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return the payAdvance
	 */
	public BigDecimal getPayAdvance() {
		return payAdvance;
	}
	/**
	 * @param payAdvance the payAdvance to set
	 */
	public void setPayAdvance(BigDecimal payAdvance) {
		this.payAdvance = payAdvance;
	}
	/**
	 * @return the customDiscount
	 */
	public BigDecimal getCustomDiscount() {
		return customDiscount;
	}
	/**
	 * @param customDiscount the customDiscount to set
	 */
	public void setCustomDiscount(BigDecimal customDiscount) {
		this.customDiscount = customDiscount;
	}
	/**
	 * @return the totalMoney
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the refund
	 */
	public Integer getRefund() {
		return refund;
	}
	/**
	 * @param refund the refund to set
	 */
	public void setRefund(Integer refund) {
		this.refund = refund;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * @return the sumOrder
	 */
	public Integer getSumOrder() {
		return sumOrder;
	}
	/**
	 * @param sumOrder the sumOrder to set
	 */
	public void setSumOrder(Integer sumOrder) {
		this.sumOrder = sumOrder;
	}
	/**
	 * @return the sumOrderMoney
	 */
	public Integer getSumOrderMoney() {
		return sumOrderMoney;
	}
	/**
	 * @param sumOrderMoney the sumOrderMoney to set
	 */
	public void setSumOrderMoney(Integer sumOrderMoney) {
		this.sumOrderMoney = sumOrderMoney;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((customDiscount == null) ? 0 : customDiscount.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + limit;
		result = prime * result + ((memberNumber == null) ? 0 : memberNumber.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + page;
		result = prime * result + ((payAdvance == null) ? 0 : payAdvance.hashCode());
		result = prime * result + ((payMethod == null) ? 0 : payMethod.hashCode());
		result = prime * result + ((refund == null) ? 0 : refund.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((sumOrder == null) ? 0 : sumOrder.hashCode());
		result = prime * result + ((sumOrderMoney == null) ? 0 : sumOrderMoney.hashCode());
		result = prime * result + ((totalMoney == null) ? 0 : totalMoney.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderVo other = (OrderVo) obj;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (customDiscount == null) {
			if (other.customDiscount != null)
				return false;
		} else if (!customDiscount.equals(other.customDiscount))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (limit != other.limit)
			return false;
		if (memberNumber == null) {
			if (other.memberNumber != null)
				return false;
		} else if (!memberNumber.equals(other.memberNumber))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (page != other.page)
			return false;
		if (payAdvance == null) {
			if (other.payAdvance != null)
				return false;
		} else if (!payAdvance.equals(other.payAdvance))
			return false;
		if (payMethod == null) {
			if (other.payMethod != null)
				return false;
		} else if (!payMethod.equals(other.payMethod))
			return false;
		if (refund == null) {
			if (other.refund != null)
				return false;
		} else if (!refund.equals(other.refund))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		if (shopName == null) {
			if (other.shopName != null)
				return false;
		} else if (!shopName.equals(other.shopName))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (sumOrder == null) {
			if (other.sumOrder != null)
				return false;
		} else if (!sumOrder.equals(other.sumOrder))
			return false;
		if (sumOrderMoney == null) {
			if (other.sumOrderMoney != null)
				return false;
		} else if (!sumOrderMoney.equals(other.sumOrderMoney))
			return false;
		if (totalMoney == null) {
			if (other.totalMoney != null)
				return false;
		} else if (!totalMoney.equals(other.totalMoney))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderVo [id=" + id + ", number=" + number + ", memberNumber=" + memberNumber + ", createTime="
				+ createTime + ", shopId=" + shopId + ", shopName=" + shopName + ", payMethod=" + payMethod
				+ ", bankName=" + bankName + ", cardNumber=" + cardNumber + ", payAdvance=" + payAdvance
				+ ", customDiscount=" + customDiscount + ", totalMoney=" + totalMoney + ", state=" + state + ", remark="
				+ remark + ", refund=" + refund + ", page=" + page + ", limit=" + limit + ", count=" + count
				+ ", sumOrder=" + sumOrder + ", sumOrderMoney=" + sumOrderMoney + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
	
}
