package com.cashier.entity;
import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @ClassName: Activities
 * @description 活动商品表（云小讴收银---周嘉鑫）
 * @author zhoujiaxin
 * @createDate 2019年6月18日
 */
public class Activities implements Serializable{

	/** 序列号 */
	private static final long serialVersionUID = 1L;
	/** 活动商品表id */
	private BigInteger id;
	/** 活动id */
	private BigInteger specialOffersId;
	/** 商品id */
	private BigInteger productId;
    
	
    public Activities() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     * @param specialOffersId
     * @param productId
     */
    public Activities(BigInteger id, BigInteger specialOffersId, BigInteger productId) {
        super();
        this.id = id;
        this.specialOffersId = specialOffersId;
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((specialOffersId == null) ? 0 : specialOffersId.hashCode());
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
        Activities other = (Activities) obj;
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
        if (specialOffersId == null) {
            if (other.specialOffersId != null)
                return false;
        } else if (!specialOffersId.equals(other.specialOffersId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Activities [id=" + id + ", specialOffersId=" + specialOffersId + ", productId=" + productId + "]";
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getSpecialOffersId() {
        return specialOffersId;
    }

    public void setSpecialOffersId(BigInteger specialOffersId) {
        this.specialOffersId = specialOffersId;
    }

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }
	
		
	
	
}
