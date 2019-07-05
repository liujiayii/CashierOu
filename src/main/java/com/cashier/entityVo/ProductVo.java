package com.cashier.entityVo;

import java.io.Serializable;
import java.util.List;

import com.cashier.entity.Product;
import com.cashier.entity.ProductType;

public class ProductVo extends ProductType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProductOnDisplayVo> listProduct;

	
	public List<ProductOnDisplayVo> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ProductOnDisplayVo> listProduct) {
		this.listProduct = listProduct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((listProduct == null) ? 0 : listProduct.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductVo other = (ProductVo) obj;
		if (listProduct == null) {
			if (other.listProduct != null)
				return false;
		} else if (!listProduct.equals(other.listProduct))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductVo [listProduct=" + listProduct + "]";
	}
	
}
