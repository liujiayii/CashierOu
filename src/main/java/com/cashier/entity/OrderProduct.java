package com.cashier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
/**
 * 
 *
 * @description 订单商品表
 *
 * @author chenshuxian
 * @createDate 2018年11月8日
 */
public class OrderProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	/**订单商品id*/
	private BigInteger id;
	/**商品id*/
	private BigInteger productId;
	/**商品数量*/
	private Integer productCount;
	/**订单号*/
	private String orderNumber;
	/**活动id*/
	private BigInteger soId;
	/** 商品类型 1商品、2套餐 */
	private Integer type;
	/**商品销售价 */
	private BigDecimal salePrice;
	public BigDecimal getMemberPricediscount() {
		return memberPricediscount;
	}
	public void setMemberPricediscount(BigDecimal memberPricediscount) {
		this.memberPricediscount = memberPricediscount;
	}
	/**商品总价价 */
	private BigDecimal memberPrice;

	/**实际付款 */
	private BigDecimal memberPricediscount;

	

    public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public BigDecimal getMemberPrice() {
		return memberPrice;
	}
	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}
	/**
     * 
     */
    public OrderProduct() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @param id
     * @param productId
     * @param productCount
     * @param orderNumber
     * @param soId
     * @param type
     */
    public OrderProduct(BigInteger id, BigInteger productId, Integer productCount, String orderNumber, BigInteger soId,
            Integer type) {
        super();
        this.id = id;
        this.productId = productId;
        this.productCount = productCount;
        this.orderNumber = orderNumber;
        this.soId = soId;
        this.type = type;
    }
    @Override
	public String toString() {
		return "OrderProduct [id=" + id + ", productId=" + productId + ", productCount=" + productCount
				+ ", orderNumber=" + orderNumber + ", soId=" + soId + ", type=" + type + ", salePrice=" + salePrice
				+ ", memberPrice=" + memberPrice + ", memberPricediscount="
				+ memberPricediscount + "]";
	}
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        result = prime * result + ((productCount == null) ? 0 : productCount.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
        OrderProduct other = (OrderProduct) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (orderNumber == null) {
            if (other.orderNumber != null)
                return false;
        } else if (!orderNumber.equals(other.orderNumber))
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
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
	
}
