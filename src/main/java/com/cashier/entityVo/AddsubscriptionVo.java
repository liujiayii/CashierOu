/**
 * @Title: AddsubscriptionVo.java
 * @Package com.cashier.entityVo
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Administrator
 * @date 2018年11月23日
 */
package com.cashier.entityVo;

import java.io.Serializable;
import java.util.List;

import com.cashier.entity.Product;

/**
 * @ClassName: AddsubscriptionVo
 * @description 用一句话描述这个类的作用
 * @author 姓名全拼
 * @createDate 2018年11月23日
 */
public class AddsubscriptionVo implements Serializable{
    /** 序列号 */
    private static final long serialVersionUID = 1L;
    /** 商品分类id */
    private Integer productTypeId ;
    /** 商品分类名称 */
    private String  productTypeName ;
    /** 商品类 */
    private List<Product> product ;
   
    
    public AddsubscriptionVo(){
        super();
    }
    public Integer getProductTypeId() {
        return productTypeId;
    }
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
    public String getProductTypeName() {
        return productTypeName;
    }
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
    public List<Product> getProduct() {
        return product;
    }
    public void setProduct(List<Product> product) {
        this.product = product;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
        result = prime * result + ((productTypeName == null) ? 0 : productTypeName.hashCode());
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
        AddsubscriptionVo other = (AddsubscriptionVo) obj;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (productTypeId == null) {
            if (other.productTypeId != null)
                return false;
        } else if (!productTypeId.equals(other.productTypeId))
            return false;
        if (productTypeName == null) {
            if (other.productTypeName != null)
                return false;
        } else if (!productTypeName.equals(other.productTypeName))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "AddsubscriptionVo [productTypeId=" + productTypeId + ", productTypeName=" + productTypeName
                + ", product=" + product + "]";
    }
    public AddsubscriptionVo(Integer productTypeId, String productTypeName, List<Product> product) {
        super();
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
        this.product = product;
    }
 
    
    
}
