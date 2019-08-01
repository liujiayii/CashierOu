package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.cashier.entity.Permission;

/**
 *
 * @ClassName: Permission
 * @description 权限表
 * @author zhoujiaxin
 * @createDate 2018年11月16日
 */



public class PermissionVo implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    /** 父权限ID */
    private BigInteger parentIds;
    /** 父权限名称 */
    private String parent_names;
    
    private List<Permission> permissions;

    /**
     * 
     */
    public PermissionVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param parentIds
     * @param parent_names
     * @param permissions
     */
    public PermissionVo(BigInteger parentIds, String parent_names, List<Permission> permissions) {
        super();
        this.parentIds = parentIds;
        this.parent_names = parent_names;
        this.permissions = permissions;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PermissionVo [parentIds=" + parentIds + ", parent_names=" + parent_names + ", permissions="
                + permissions + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parentIds == null) ? 0 : parentIds.hashCode());
        result = prime * result + ((parent_names == null) ? 0 : parent_names.hashCode());
        result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PermissionVo other = (PermissionVo) obj;
        if (parentIds == null) {
            if (other.parentIds != null)
                return false;
        } else if (!parentIds.equals(other.parentIds))
            return false;
        if (parent_names == null) {
            if (other.parent_names != null)
                return false;
        } else if (!parent_names.equals(other.parent_names))
            return false;
        if (permissions == null) {
            if (other.permissions != null)
                return false;
        } else if (!permissions.equals(other.permissions))
            return false;
        return true;
    }

    /**
     * @return the parentIds
     */
    public BigInteger getParentIds() {
        return parentIds;
    }

    /**
     * @param parentIds the parentIds to set
     */
    public void setParentIds(BigInteger parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * @return the parent_names
     */
    public String getParent_names() {
        return parent_names;
    }

    /**
     * @param parent_names the parent_names to set
     */
    public void setParent_names(String parent_names) {
        this.parent_names = parent_names;
    }

    /**
     * @return the permissions
     */
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
		
}
