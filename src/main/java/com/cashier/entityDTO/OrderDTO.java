package com.cashier.entityDTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 商品种类名称*/
	private String productType;
	/** 每种商品种类下的商品销售数量*/
	private Integer count;
	/** 此类商品总销售额*/
	private BigDecimal sumPrice;
	
	public BigDecimal getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((sumPrice == null) ? 0 : sumPrice.hashCode());
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
		OrderDTO other = (OrderDTO) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (sumPrice == null) {
			if (other.sumPrice != null)
				return false;
		} else if (!sumPrice.equals(other.sumPrice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderDTO [productType=" + productType + ", count=" + count + ", sumPrice=" + sumPrice + "]";
	}
	
}
