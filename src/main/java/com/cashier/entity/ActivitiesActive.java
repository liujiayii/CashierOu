/**
 *
 * @Title: ActivitiesActive.java
 * @Package com.cashier.entity
 * @Description: TODO)
 * @author zhoujiaxin
 * @date 2019年6月26日
 */

package com.cashier.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 
 * @Description 动态活动商品表 (主要用于同一店铺中一个商品只能参加一个活动)
 *    
 * @author zhoujiaxin  
 * @createDate 2019年6月26日  
 */
public class ActivitiesActive implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /** 动态活动商品表ID */
    private BigInteger id;
    /** 活动id */
    private BigInteger activityId;
    /** 活动名称 */
    private String activityName;
    /** 商品id */
    private BigInteger productId;
    /** 分店id */
    private BigInteger shopId;
    /**
     * 
     */
    public ActivitiesActive() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @param id
     * @param activityId
     * @param activityName
     * @param productId
     * @param shopId
     */
    public ActivitiesActive(BigInteger id, BigInteger activityId, String activityName, BigInteger productId,
            BigInteger shopId) {
        super();
        this.id = id;
        this.activityId = activityId;
        this.activityName = activityName;
        this.productId = productId;
        this.shopId = shopId;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activityId == null) ? 0 : activityId.hashCode());
        result = prime * result + ((activityName == null) ? 0 : activityName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
        ActivitiesActive other = (ActivitiesActive) obj;
        if (activityId == null) {
            if (other.activityId != null)
                return false;
        } else if (!activityId.equals(other.activityId))
            return false;
        if (activityName == null) {
            if (other.activityName != null)
                return false;
        } else if (!activityName.equals(other.activityName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
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
        return "ActivitiesActive [id=" + id + ", activityId=" + activityId + ", activityName=" + activityName
                + ", productId=" + productId + ", shopId=" + shopId + "]";
    }
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public BigInteger getActivityId() {
        return activityId;
    }
    public void setActivityId(BigInteger activityId) {
        this.activityId = activityId;
    }
    public String getActivityName() {
        return activityName;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    public BigInteger getProductId() {
        return productId;
    }
    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }
    public BigInteger getShopId() {
        return shopId;
    }
    public void setShopId(BigInteger shopId) {
        this.shopId = shopId;
    }
    
}
