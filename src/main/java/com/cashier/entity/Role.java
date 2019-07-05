package com.cashier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
*
* @ClassName: Role
* @description 角色表
*
* @author zhoujiaxin
* @createDate 2018年11月8日
*/
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/** 角色ID */
	private BigInteger id;
	/** 角色名称 */
	private String name;
	/** 角色工资 */
	private BigDecimal salary;
	/** 备注 */
	private String remarks;
	/** 所属店铺ID */
    private BigInteger shopId;
    /**
     * @return the id
     */
    public BigInteger getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(BigInteger id) {
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the salary
     */
    public BigDecimal getSalary() {
        return salary;
    }
    /**
     * @param salary the salary to set
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }
    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    /**
     * @return the shopId
     */
    public BigInteger getShopId() {
        return shopId;
    }
    /**
     * @param shopId the shopId to set
     */
    public void setShopId(BigInteger shopId) {
        this.shopId = shopId;
    }
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    /**
     * @Title: toString
     * @description 
     * @return 
     * @return     
     * @author zhoujiaxin
     * @createDate 2018年11月16日
     */
    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + ", salary=" + salary + ", remarks=" + remarks + ", shopId="
                + shopId + "]";
    }
    /**
     * @Title: hashCode
     * @description 
     * @return 
     * @return     
     * @author zhoujiaxin
     * @createDate 2018年11月16日
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
        result = prime * result + ((salary == null) ? 0 : salary.hashCode());
        result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
        return result;
    }
    /**
     * @Title: equals
     * @description 
     * @param obj
     * @return 
     * @return     
     * @author zhoujiaxin
     * @createDate 2018年11月16日
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (remarks == null) {
            if (other.remarks != null)
                return false;
        } else if (!remarks.equals(other.remarks))
            return false;
        if (salary == null) {
            if (other.salary != null)
                return false;
        } else if (!salary.equals(other.salary))
            return false;
        if (shopId == null) {
            if (other.shopId != null)
                return false;
        } else if (!shopId.equals(other.shopId))
            return false;
        return true;
    }
    /**
     * @param id
     * @param name
     * @param salary
     * @param remarks
     * @param shopId
     */
    public Role(BigInteger id, String name, BigDecimal salary, String remarks, BigInteger shopId) {
        super();
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.remarks = remarks;
        this.shopId = shopId;
    }
    /**
     * 
     */
    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	
	
}
