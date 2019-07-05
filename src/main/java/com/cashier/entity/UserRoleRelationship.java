package com.cashier.entity;

import java.io.Serializable;
/**
 *
 * @ClassName: UserRoleRelationship
 * @description 用户角色关系表
 *
 * @author zhoujiaxin
 * @createDate 2018年11月16日
 */
import java.math.BigInteger;

public class UserRoleRelationship implements Serializable{

    private static final long serialVersionUID = 1L;
    
    /** 用户角色关系表ID */
    private BigInteger id;
    /** 用户ID */
    private BigInteger userId;
    /** 角色ID */
    private BigInteger roleId;
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
     * @return the userId
     */
    public BigInteger getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
    /**
     * @return the roleId
     */
    public BigInteger getRoleId() {
        return roleId;
    }
    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(BigInteger roleId) {
        this.roleId = roleId;
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
        return "UserRoleRelationship [id=" + id + ", userId=" + userId + ", roleId=" + roleId + "]";
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
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        UserRoleRelationship other = (UserRoleRelationship) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
    /**
     * @param id
     * @param userId
     * @param roleId
     */
    public UserRoleRelationship(BigInteger id, BigInteger userId, BigInteger roleId) {
        super();
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }
    /**
     * 
     */
    public UserRoleRelationship() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
   
}
