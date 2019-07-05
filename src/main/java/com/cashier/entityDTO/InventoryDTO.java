/**
 * @Title: InventoryDTO.java
 * @Package com.cashier.entityDTO
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Administrator
 * @date 2019年7月3日
 */
package com.cashier.entityDTO;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: InventoryDTO
 * @description 用一句话描述这个类的作用
 * @author 姓名全拼
 * @createDate 2019年7月3日
 */
public class InventoryDTO implements Serializable {

    /**  字段的含义 */
    private static final long serialVersionUID = 1L;
    
    /** 备注ID */
    private BigInteger id;
    /** 店铺ID */
    private BigInteger shopId;
    /** 库存预警 */
    private BigInteger inventoryWarning;
    /** 页数*/
    private Integer page;
    /** 行数*/
    private Integer limit;
    /** 行数*/
    private BigInteger productTypeId;
    
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
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
    public BigInteger getProductTypeId() {
        return productTypeId;
    }
    public void setProductTypeId(BigInteger productTypeId) {
        this.productTypeId = productTypeId;
    }
    @Override
    public String toString() {
        return "InventoryDTO [id=" + id + ", shopId=" + shopId + ", inventoryWarning=" + inventoryWarning + ", page="
                + page + ", limit=" + limit + ", productTypeId=" + productTypeId + "]";
    }
    public InventoryDTO(BigInteger id, BigInteger shopId, BigInteger inventoryWarning, Integer page, Integer limit,
            BigInteger productTypeId) {
        super();
        this.id = id;
        this.shopId = shopId;
        this.inventoryWarning = inventoryWarning;
        this.page = page;
        this.limit = limit;
        this.productTypeId = productTypeId;
    }
    public InventoryDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((inventoryWarning == null) ? 0 : inventoryWarning.hashCode());
        result = prime * result + ((limit == null) ? 0 : limit.hashCode());
        result = prime * result + ((page == null) ? 0 : page.hashCode());
        result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
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
        InventoryDTO other = (InventoryDTO) obj;
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
        if (productTypeId == null) {
            if (other.productTypeId != null)
                return false;
        } else if (!productTypeId.equals(other.productTypeId))
            return false;
        if (shopId == null) {
            if (other.shopId != null)
                return false;
        } else if (!shopId.equals(other.shopId))
            return false;
        return true;
    }
    
    
}
