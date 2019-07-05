package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @ClassName: OrderProductVo
 * @description 订单商品表的Vo实体类
 *
 * @author dujiawei
 * @createDate 2019年6月21日
 */
public class OrderProductVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**订单商品id*/
	private BigInteger id;
	/**商品id*/
	private BigInteger productId;
	
	/**商品名称 */
	private String productName;
	/**商品分类id */
	private BigInteger productTypeId;
	/**商品分类名称 */
	private String productType;
	/**商品销售价 */
	private BigDecimal salePrice;
	/**商品会员价 */
	private  BigDecimal memberPrice;
	
	/**商品数量*/
	private Integer productCount;
	/**订单号*/
	private String orderNumber;
	/**活动id*/
	private BigInteger soId;
	/** 商品类型 1商品、2套餐 */
	private Integer type;
	/** 当前所在页 */
	private int page = 1;
	/** 每页显示条数 */
	private int limit = 5;
	/** 数据条数 */
	private Integer count;


	/**实际付款金额 */
	private BigDecimal memberPricediscount;
	
	public BigDecimal getMemberPricediscount() {
		return memberPricediscount;
	}
	public void setMemberPricediscount(BigDecimal memberPricediscount) {
		this.memberPricediscount = memberPricediscount;
	}
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
	 * @return the productId
	 */
	public BigInteger getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productTypeId
	 */
	public BigInteger getProductTypeId() {
		return productTypeId;
	}
	/**
	 * @param productTypeId the productTypeId to set
	 */
	public void setProductTypeId(BigInteger productTypeId) {
		this.productTypeId = productTypeId;
	}
	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return the salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * @return the memberPrice
	 */
	public BigDecimal getMemberPrice() {
		return memberPrice;
	}
	/**
	 * @param memberPrice the memberPrice to set
	 */
	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}
	/**
	 * @return the productCount
	 */
	public Integer getProductCount() {
		return productCount;
	}
	/**
	 * @param productCount the productCount to set
	 */
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * @return the soId
	 */
	public BigInteger getSoId() {
		return soId;
	}
	/**
	 * @param soId the soId to set
	 */
	public void setSoId(BigInteger soId) {
		this.soId = soId;
	}
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + limit;
		result = prime * result + ((memberPrice == null) ? 0 : memberPrice.hashCode());
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		result = prime * result + page;
		result = prime * result + ((productCount == null) ? 0 : productCount.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
		result = prime * result + ((salePrice == null) ? 0 : salePrice.hashCode());
		result = prime * result + ((soId == null) ? 0 : soId.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		OrderProductVo other = (OrderProductVo) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (limit != other.limit)
			return false;
		if (memberPrice == null) {
			if (other.memberPrice != null)
				return false;
		} else if (!memberPrice.equals(other.memberPrice))
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		if (page != other.page)
			return false;
		if (productCount == null) {
			if (other.productCount != null)
				return false;
		} else if (!productCount.equals(other.productCount))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (productTypeId == null) {
			if (other.productTypeId != null)
				return false;
		} else if (!productTypeId.equals(other.productTypeId))
			return false;
		if (salePrice == null) {
			if (other.salePrice != null)
				return false;
		} else if (!salePrice.equals(other.salePrice))
			return false;
		if (soId == null) {
			if (other.soId != null)
				return false;
		} else if (!soId.equals(other.soId))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderProductVo [id=" + id + ", productId=" + productId + ", productName=" + productName
				+ ", productTypeId=" + productTypeId + ", productType=" + productType + ", salePrice=" + salePrice
				+ ", memberPrice=" + memberPrice + ", productCount=" + productCount + ", orderNumber=" + orderNumber
				+ ", soId=" + soId + ", type=" + type + ", page=" + page + ", limit=" + limit + ", count=" + count
				+ ", memberPricediscount=" + memberPricediscount + "]";
	}

}
