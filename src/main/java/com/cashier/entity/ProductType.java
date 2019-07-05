package com.cashier.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
/**
 * 商品类型表实体类
 *
 * @author chenshuxian
 * @createDate 2019年6月18日 
 */
public class ProductType implements Serializable {

	private static final long serialVersionUID = 1L;
	/**商品分类id */
	private BigInteger id;
	/** 商品分类名称 */
	private String productTypeName;
	/**店铺id */
	private BigInteger shopId;
	/**是否可用,软删除（1：可用/2：不可用） */
	private Integer canUse;
	
	private List<Product> products;
	
	public ProductType() {
		super();
	}
	
	public ProductType(BigInteger id, String productTypeName, BigInteger shopId, Integer canUse,
			List<Product> products) {
		super();
		this.id = id;
		this.productTypeName = productTypeName;
		this.shopId = shopId;
		this.canUse = canUse;
		this.products = products;
	}

	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public BigInteger getShopId() {
		return shopId;
	}
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}
	public Integer getCanUse() {
		return canUse;
	}
	public void setCanUse(Integer canUse) {
		this.canUse = canUse;
	}
	
	public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((canUse == null) ? 0 : canUse.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((productTypeName == null) ? 0 : productTypeName.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
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
		ProductType other = (ProductType) obj;
		if (canUse == null) {
			if (other.canUse != null)
				return false;
		} else if (!canUse.equals(other.canUse))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productTypeName == null) {
			if (other.productTypeName != null)
				return false;
		} else if (!productTypeName.equals(other.productTypeName))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", productType=" + productTypeName + ", shopId=" + shopId + ", canUse=" + canUse
				+ "]";
	}
	
}
