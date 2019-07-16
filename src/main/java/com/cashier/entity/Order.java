package com.cashier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 *
 * @ClassName: Order
 * @description 消费订单表
 *
 * @author dujiawei
 * @createDate 2019年6月21日
 */
public class Order implements Serializable {

	/** UID */
	private static final long serialVersionUID = 1L;
	/** 消费订单id */
	private BigInteger id;
	/** 消费订单编号 */
	private String number;
	/** 会员卡号码 */
	private String memberNumber;
	/** 订单创建时间 */
	private Timestamp createTime;
	/** 店铺id */
	private BigInteger shopId;
	/** 店铺名 */
	private String shopName;

	/** 交易方式：1现金、2POS机、3支付宝、4微信支付 */
	private Integer payMethod;

	/** 开户行名称 */
	
	  private String bankName;
	 /** 银行卡号 */
     private String cardNumber;
					 

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/** 订单应收金额 */
	private BigDecimal payAdvance;
	/** 手动输入折扣 */
	private BigDecimal customDiscount;
	/** 实付总金额 */
	private BigDecimal totalMoney;
	/** 订单状态（1出售、2退货） */
	private Integer state;
	/** 备注 */
	private String remark;
	/**
	 * 退货状态： 1.退货了 2.没退货
	 */
	private Integer refund;

	/** 数据条数 */
	private Integer count;

	public String getOuttradeno() {
		return outtradeno;
	}

	public void setOuttradeno(String outtradeno) {
		this.outtradeno = outtradeno;
	}

	/**
	 * 支付宝微信付款单号
	 */
	private String outtradeno;

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
	/*
	 * public String getBankName() { return bankName; }
	 * 
	 *//**
		 * @param bankName the bankName to set
		 */
	/*
	 * public void setBankName(String bankName) { this.bankName = bankName; }
	 * 
	 *//**
		 * @return the cardNumber
		 */
	/*
	 * public String getCardNumber() { return cardNumber; }
	 * 
	 *//**
		 * @param cardNumber the cardNumber to set
		 *//*
			 * public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber;
			 * }
			 */

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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		/*
		 * result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		 * result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		 */
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((customDiscount == null) ? 0 : customDiscount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberNumber == null) ? 0 : memberNumber.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((payAdvance == null) ? 0 : payAdvance.hashCode());
		result = prime * result + ((payMethod == null) ? 0 : payMethod.hashCode());
		result = prime * result + ((refund == null) ? 0 : refund.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((totalMoney == null) ? 0 : totalMoney.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Order other = (Order) obj;
		/*
		 * if (bankName == null) { if (other.bankName != null) return false; } else if
		 * (!bankName.equals(other.bankName)) return false;
		 * 
		 * if (cardNumber == null) { if (other.cardNumber != null) return false; } else
		 * if (!cardNumber.equals(other.cardNumber)) return false;
		 */
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (totalMoney == null) {
			if (other.totalMoney != null)
				return false;
		} else if (!totalMoney.equals(other.totalMoney))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", number=" + number + ", memberNumber=" + memberNumber + ", createTime="
				+ createTime + ", shopId=" + shopId + ", shopName=" + shopName + ", payMethod=" + payMethod
				+ ", bankName=" + bankName + ", cardNumber=" + cardNumber + ", payAdvance=" + payAdvance
				+ ", customDiscount=" + customDiscount + ", totalMoney=" + totalMoney + ", state=" + state + ", remark="
				+ remark + ", refund=" + refund + ", count=" + count + ", outtradeno=" + outtradeno + "]";
	}

}
