package com.cashier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @ClassName: GoodstrafficOrdersProduct
 * @description 存放货流订单产品表的属性
 * @author chenshuxian
 * @createDate 2019年6月19日
 */

public class GoodstrafficOrdersProduct implements Serializable{
	
    /** Serializable序列化 */
    private static final long serialVersionUID = 1L;
    /** 货流订单产品表ID */
	private BigInteger id ;
	/** 货流管理表ID */
	private BigInteger goodstrafficManagementId ;
	/** 数量 */
	private BigInteger quantity ;
	/** 商品ID */
	private BigInteger productId ;
	/** 金额 */
	private BigDecimal money ;
	public GoodstrafficOrdersProduct(){
	    super();
	}
	
    public GoodstrafficOrdersProduct(BigInteger id, BigInteger goodstrafficManagementId, BigInteger quantity,
			BigInteger productId, BigDecimal money) {
		super();
		this.id = id;
		this.goodstrafficManagementId = goodstrafficManagementId;
		this.quantity = quantity;
		this.productId = productId;
		this.money = money;
	}

	public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public BigInteger getGoodstrafficManagementId() {
        return goodstrafficManagementId;
    }
    public void setGoodstrafficManagementId(BigInteger goodstrafficManagementId) {
        this.goodstrafficManagementId = goodstrafficManagementId;
    }
    public BigInteger getQuantity() {
        return quantity;
    }
    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }
    public BigInteger getProductId() {
        return productId;
    }
    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }
   
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodstrafficManagementId == null) ? 0 : goodstrafficManagementId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		GoodstrafficOrdersProduct other = (GoodstrafficOrdersProduct) obj;
		if (goodstrafficManagementId == null) {
			if (other.goodstrafficManagementId != null)
				return false;
		} else if (!goodstrafficManagementId.equals(other.goodstrafficManagementId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GoodstrafficOrdersProduct [id=" + id + ", goodstrafficManagementId=" + goodstrafficManagementId
				+ ", quantity=" + quantity + ", productId=" + productId + ", money=" + money + "]";
	}
   
    
	
	
}
