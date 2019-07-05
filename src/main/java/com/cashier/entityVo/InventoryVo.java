package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class InventoryVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger id;
	/** 商品名称 */
	private String productName;
	/** 数量*/
	private BigInteger quantity;
	/** 进货价*/
	private BigDecimal pleased;
	/** 分类名称*/
	private String productTypeName;
	/** 库存预警 */
    private BigInteger inventoryWarning;
    /** 店铺ID */
    private BigInteger shopId;
	/** 页数*/
	private Integer page;
	/** 行数*/
	private Integer limit;
	
	public InventoryVo() {
		super();
	}

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPleased() {
        return pleased;
    }

    public void setPleased(BigDecimal pleased) {
        this.pleased = pleased;
    }

   
    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public BigInteger getInventoryWarning() {
        return inventoryWarning;
    }

    public void setInventoryWarning(BigInteger inventoryWarning) {
        this.inventoryWarning = inventoryWarning;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public BigInteger getShopId() {
        return shopId;
    }

    public void setShopId(BigInteger shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "InventoryVo [id=" + id + ", productName=" + productName + ", quantity=" + quantity + ", pleased="
                + pleased + ", productTypeName=" + productTypeName + ", inventoryWarning=" + inventoryWarning + ", shopId="
                + shopId + ", page=" + page + ", limit=" + limit + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productTypeName == null) ? 0 : productTypeName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((inventoryWarning == null) ? 0 : inventoryWarning.hashCode());
        result = prime * result + ((limit == null) ? 0 : limit.hashCode());
        result = prime * result + ((page == null) ? 0 : page.hashCode());
        result = prime * result + ((pleased == null) ? 0 : pleased.hashCode());
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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
        InventoryVo other = (InventoryVo) obj;
        if (productTypeName == null) {
            if (other.productTypeName != null)
                return false;
        } else if (!productTypeName.equals(other.productTypeName))
            return false;
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
        if (limit == null) {
            if (other.limit != null)
                return false;
        } else if (!limit.equals(other.limit))
            return false;
        if (page == null) {
            if (other.page != null)
                return false;
        } else if (!page.equals(other.page))
            return false;
        if (pleased == null) {
            if (other.pleased != null)
                return false;
        } else if (!pleased.equals(other.pleased))
            return false;
        if (productName == null) {
            if (other.productName != null)
                return false;
        } else if (!productName.equals(other.productName))
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

    public InventoryVo(BigInteger id, String productName, BigInteger quantity, BigDecimal pleased, String productTypeName,
            BigInteger inventoryWarning, BigInteger shopId, Integer page, Integer limit) {
        super();
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.pleased = pleased;
        this.productTypeName = productTypeName;
        this.inventoryWarning = inventoryWarning;
        this.shopId = shopId;
        this.page = page;
        this.limit = limit;
    }

   

	
	
}
