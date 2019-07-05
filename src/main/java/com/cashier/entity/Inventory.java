package com.cashier.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class Inventory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger id;
	/** 商品ID */
	private BigInteger productId;
	/** 库存数量 */
	private BigInteger quantity;
	/** 购买状态 1:申购 2:采购 */
	private Integer purchaseState;
	/** 店铺ID */
	private BigInteger shopId;
	/** 库存预警 */
	private BigInteger inventoryWarning;
	
	
	public Inventory() {
		super();
	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public BigInteger getQuantity() {
		return quantity;
	}
    
	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}
	
	public Integer getPurchaseState() {
		return purchaseState;
	}
	
	public void setPurchaseState(Integer purchaseState) {
		this.purchaseState = purchaseState;
	}
	
	public BigInteger getShopId() {
		return shopId;
	}
	
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}
	
    public BigInteger getInventoryWarning() {
        return inventoryWarning;
    }

    public void setInventoryWarning(BigInteger inventoryWarning) {
        this.inventoryWarning = inventoryWarning;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((inventoryWarning == null) ? 0 : inventoryWarning.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((purchaseState == null) ? 0 : purchaseState.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
        Inventory other = (Inventory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (inventoryWarning == null) {
            if (other.inventoryWarning != null)
                return false;
        } else if (!inventoryWarning.equals(other.inventoryWarning))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (purchaseState == null) {
            if (other.purchaseState != null)
                return false;
        } else if (!purchaseState.equals(other.purchaseState))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
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
        return "Inventory [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", purchaseState="
                + purchaseState + ", shopId=" + shopId + ", inventoryWarning=" + inventoryWarning + "]";
    }

   
	
	
}
