/**
 * @Title: GoodstrafficOrdersProductVo.java
 * @Package com.cashier.entityVo
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Administrator
 * @date 2018年11月21日
 */
package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @ClassName: GoodstrafficOrdersProductVo
 * @description 用一句话描述这个类的作用
 * @author 姓名全拼
 * @createDate 2018年11月21日
 */
public class GoodstrafficOrdersProductVo implements Serializable {
    /** 序列号 */
    private static final long serialVersionUID = 1L;
    /** 采购详情表ID */
    private BigInteger id ;
    /** 数量 */
    private BigInteger quantity ;
    /** 金额 */
    private BigDecimal money ;
    /** 产品名称 */
    private String productName ;
    /** 产品分类 */
    private String productTypeName ;   
    /** 产品名称ID */
    private BigInteger productId ;
    
    
    public GoodstrafficOrdersProductVo() {
        super();
        // TODO Auto-generated constructor stub
    }


    public BigInteger getId() {
        return id;
    }


    public void setId(BigInteger id) {
        this.id = id;
    }


    public BigInteger getQuantity() {
        return quantity;
    }


    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }


    public BigDecimal getMoney() {
        return money;
    }


    public void setMoney(BigDecimal money) {
        this.money = money;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductTypeName() {
        return productTypeName;
    }


    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    
    public BigInteger getProductId() {
        return productId;
    }


    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }


    @Override
    public String toString() {
        return "GoodstrafficOrdersProductVo [id=" + id + ", quantity=" + quantity + ", money=" + money
                + ", productName=" + productName + ", productTypeName=" + productTypeName + ", productId=" + productId
                + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((money == null) ? 0 : money.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        result = prime * result + ((productTypeName == null) ? 0 : productTypeName.hashCode());
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
        GoodstrafficOrdersProductVo other = (GoodstrafficOrdersProductVo) obj;
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
        if (productName == null) {
            if (other.productName != null)
                return false;
        } else if (!productName.equals(other.productName))
            return false;
        if (productTypeName == null) {
            if (other.productTypeName != null)
                return false;
        } else if (!productTypeName.equals(other.productTypeName))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        return true;
    }


    public GoodstrafficOrdersProductVo(BigInteger id, BigInteger quantity, BigDecimal money, String productName,
            String productTypeName, BigInteger productId) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.money = money;
        this.productName = productName;
        this.productTypeName = productTypeName;
        this.productId = productId;
    }


    
    
    
}
