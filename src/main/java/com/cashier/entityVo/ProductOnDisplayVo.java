package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ProductOnDisplayVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 商品id*/
	private BigInteger id;
	/** 商品名称*/
	private String name;
	/** 商品属性列表*/
	private List<ProductProperty> listProperty;
	/**商品分类id */
	private BigInteger productTypeId;
	/**商品分类名称 */
	private String productType;
	
	
	public ProductOnDisplayVo() {
		super();
	}

	public ProductOnDisplayVo(BigInteger id, String name, List<ProductProperty> listProperty, BigInteger productTypeId,
			String productType, BigInteger quantity) {
		super();
		this.id = id;
		this.name = name;
		this.listProperty = listProperty;
		this.productTypeId = productTypeId;
		this.productType = productType;
		
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductProperty> getListProperty() {
		return listProperty;
	}

	public void setListProperty(List<ProductProperty> listProperty) {
		this.listProperty = listProperty;
	}

	public BigInteger getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(BigInteger productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listProperty == null) ? 0 : listProperty.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
		
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
		ProductOnDisplayVo other = (ProductOnDisplayVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listProperty == null) {
			if (other.listProperty != null)
				return false;
		} else if (!listProperty.equals(other.listProperty))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		
		return true;
	}

	@Override
	public String toString() {
		return "ProductOnDisplayVo [id=" + id + ", name=" + name + ", listProperty=" + listProperty + ", productTypeId="
				+ productTypeId + ", productType=" + productType + "]";
	}
	
	
}
