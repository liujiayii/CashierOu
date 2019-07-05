package com.cashier.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @ClassName: Permission
 * @description 权限表
 * @author zhoujiaxin
 * @createDate 2018年11月16日
 */

public class Permission implements Serializable{

    private static final long serialVersionUID = 1L;
    
    /** 权限表ID */
    private BigInteger id;
    /** 资源名称 */
    private String name;
    /** 父权限ID */
    private BigInteger parentId;
    /** 父权限名称 */
    private String parent_name;
    /** 访问url地址 */
    private String url;
    /** 映射地址 */
    private String percode;
    
    private Integer state;

    /**
     * 
     */
    public Permission() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     * @param name
     * @param parentId
     * @param parent_name
     * @param url
     * @param percode
     * @param state
     */
    public Permission(BigInteger id, String name, BigInteger parentId, String parent_name, String url, String percode,
            Integer state) {
        super();
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parent_name = parent_name;
        this.url = url;
        this.percode = percode;
        this.state = state;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Permission [id=" + id + ", name=" + name + ", parentId=" + parentId + ", parent_name=" + parent_name
                + ", url=" + url + ", percode=" + percode + ", state=" + state + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
        result = prime * result + ((parent_name == null) ? 0 : parent_name.hashCode());
        result = prime * result + ((percode == null) ? 0 : percode.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        Permission other = (Permission) obj;
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
        if (parentId == null) {
            if (other.parentId != null)
                return false;
        } else if (!parentId.equals(other.parentId))
            return false;
        if (parent_name == null) {
            if (other.parent_name != null)
                return false;
        } else if (!parent_name.equals(other.parent_name))
            return false;
        if (percode == null) {
            if (other.percode != null)
                return false;
        } else if (!percode.equals(other.percode))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

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
     * @return the parentId
     */
    public BigInteger getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the parent_name
     */
    public String getParent_name() {
        return parent_name;
    }

    /**
     * @param parent_name the parent_name to set
     */
    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the percode
     */
    public String getPercode() {
        return percode;
    }

    /**
     * @param percode the percode to set
     */
    public void setPercode(String percode) {
        this.percode = percode;
    }

    /**
     * @return the state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
