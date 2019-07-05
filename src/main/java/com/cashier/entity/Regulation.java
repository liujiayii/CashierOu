package com.cashier.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @ClassName: Regulation
 * @description 满减规则表（云小讴收银---周嘉鑫）
 * @author zhoujiaxin
 * @createDate 2019年6月18日
 */
public class Regulation implements Serializable{
	
	/** 序列号 */
	private static final long serialVersionUID = 1L;
	/** 满减规则ID */
	private BigInteger id;
	/** 活动ID */
	private BigInteger specialOffersId;
	/** 上限金额 */
	private BigDecimal money;
	/** 减去金额 */
	private BigDecimal reduceMoney;
    
    
	public Regulation() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     * @param specialOffersId
     * @param money
     * @param reduceMoney
     */
    public Regulation(BigInteger id, BigInteger specialOffersId, BigDecimal money, BigDecimal reduceMoney) {
        super();
        this.id = id;
        this.specialOffersId = specialOffersId;
        this.money = money;
        this.reduceMoney = reduceMoney;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((money == null) ? 0 : money.hashCode());
        result = prime * result + ((reduceMoney == null) ? 0 : reduceMoney.hashCode());
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
        Regulation other = (Regulation) obj;
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
        if (reduceMoney == null) {
            if (other.reduceMoney != null)
                return false;
        } else if (!reduceMoney.equals(other.reduceMoney))
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
        return "Regulation [id=" + id + ", specialOffersId=" + specialOffersId + ", money=" + money + ", reduceMoney="
                + reduceMoney + "]";
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }
    
}
