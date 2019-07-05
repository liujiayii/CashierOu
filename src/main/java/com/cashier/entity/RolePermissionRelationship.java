package com.cashier.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: RolePermissionRelationship
 * @description 角色权限关系表
 * @author zhoujiaxin
 * @createDate 2018年11月16日
 */

public class RolePermissionRelationship implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 角色权限关系表ID */
    private BigInteger id;
    /** 角色表ID */
    private BigInteger roleId;
    /** 权限表ID */
    private BigInteger permissionId;
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
     * @return the permissionId
     */
    public BigInteger getPermissionId() {
        return permissionId;
    }
    /**
     * @param permissionId the permissionId to set
     */
    public void setPermissionId(BigInteger permissionId) {
        this.permissionId = permissionId;
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
        return "RolePermissionRelationship [id=" + id + ", roleId=" + roleId + ", permissionId=" + permissionId + "]";
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
        result = prime * result + ((permissionId == null) ? 0 : permissionId.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
        RolePermissionRelationship other = (RolePermissionRelationship) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (permissionId == null) {
            if (other.permissionId != null)
                return false;
        } else if (!permissionId.equals(other.permissionId))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        return true;
    }
    /**
     * @param id
     * @param roleId
     * @param permissionId
     */
    public RolePermissionRelationship(BigInteger id, BigInteger roleId, BigInteger permissionId) {
        super();
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
    /**
     * 
     */
    public RolePermissionRelationship() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
}
