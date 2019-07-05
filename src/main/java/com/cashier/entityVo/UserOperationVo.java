package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;
/**
 *
 * @ClassName: UserOperation

 * @description 员工日常操作实体Vo类
 *
 * @author 
 * @createDate 2019年2月11日
 */

public class UserOperationVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/** 操作记录id */
    private BigInteger id;
    /** 操作人id */
    private BigInteger userUid;
    /** 操作人账号 */
    private String userUname;
    /** 操作人姓名 */
    private String name;
    /** 操作内容 */
    private String operatingContent;
    /** 权限id */
    private BigInteger permissionPid;
    /** 操作时间 */
    private Timestamp time;
    /** 月 */
	private String day;
    /** 角色id */
    private BigInteger roleRid;
    /** 店铺id */
    private BigInteger shopId;
    /** 操作记录数量 */
	private int count;
	// 接收前端发送来的分页参数
	private int page = 1;
	private int limit = 5;
    /**
     * 
     */
    public UserOperationVo() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @param id
     * @param userUid
     * @param userUname
     * @param name
     * @param operatingContent
     * @param permissionPid
     * @param time
     * @param day
     * @param roleRid
     * @param shopId
     * @param count
     * @param page
     * @param limit
     */
    public UserOperationVo(BigInteger id, BigInteger userUid, String userUname, String name, String operatingContent,
            BigInteger permissionPid, Timestamp time, String day, BigInteger roleRid, BigInteger shopId, int count,
            int page, int limit) {
        super();
        this.id = id;
        this.userUid = userUid;
        this.userUname = userUname;
        this.name = name;
        this.operatingContent = operatingContent;
        this.permissionPid = permissionPid;
        this.time = time;
        this.day = day;
        this.roleRid = roleRid;
        this.shopId = shopId;
        this.count = count;
        this.page = page;
        this.limit = limit;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserOperationVo [id=" + id + ", userUid=" + userUid + ", userUname=" + userUname + ", name=" + name
                + ", operatingContent=" + operatingContent + ", permissionPid=" + permissionPid + ", time=" + time
                + ", day=" + day + ", roleRid=" + roleRid + ", shopId=" + shopId + ", count=" + count + ", page=" + page
                + ", limit=" + limit + "]";
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + count;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + limit;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((operatingContent == null) ? 0 : operatingContent.hashCode());
        result = prime * result + page;
        result = prime * result + ((permissionPid == null) ? 0 : permissionPid.hashCode());
        result = prime * result + ((roleRid == null) ? 0 : roleRid.hashCode());
        result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((userUid == null) ? 0 : userUid.hashCode());
        result = prime * result + ((userUname == null) ? 0 : userUname.hashCode());
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
        UserOperationVo other = (UserOperationVo) obj;
        if (count != other.count)
            return false;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (limit != other.limit)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (operatingContent == null) {
            if (other.operatingContent != null)
                return false;
        } else if (!operatingContent.equals(other.operatingContent))
            return false;
        if (page != other.page)
            return false;
        if (permissionPid == null) {
            if (other.permissionPid != null)
                return false;
        } else if (!permissionPid.equals(other.permissionPid))
            return false;
        if (roleRid == null) {
            if (other.roleRid != null)
                return false;
        } else if (!roleRid.equals(other.roleRid))
            return false;
        if (shopId == null) {
            if (other.shopId != null)
                return false;
        } else if (!shopId.equals(other.shopId))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (userUid == null) {
            if (other.userUid != null)
                return false;
        } else if (!userUid.equals(other.userUid))
            return false;
        if (userUname == null) {
            if (other.userUname != null)
                return false;
        } else if (!userUname.equals(other.userUname))
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
     * @return the userUid
     */
    public BigInteger getUserUid() {
        return userUid;
    }
    /**
     * @param userUid the userUid to set
     */
    public void setUserUid(BigInteger userUid) {
        this.userUid = userUid;
    }
    /**
     * @return the userUname
     */
    public String getUserUname() {
        return userUname;
    }
    /**
     * @param userUname the userUname to set
     */
    public void setUserUname(String userUname) {
        this.userUname = userUname;
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
     * @return the operatingContent
     */
    public String getOperatingContent() {
        return operatingContent;
    }
    /**
     * @param operatingContent the operatingContent to set
     */
    public void setOperatingContent(String operatingContent) {
        this.operatingContent = operatingContent;
    }
    /**
     * @return the permissionPid
     */
    public BigInteger getPermissionPid() {
        return permissionPid;
    }
    /**
     * @param permissionPid the permissionPid to set
     */
    public void setPermissionPid(BigInteger permissionPid) {
        this.permissionPid = permissionPid;
    }
    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
    }
    /**
     * @param time the time to set
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }
    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }
    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }
    /**
     * @return the roleRid
     */
    public BigInteger getRoleRid() {
        return roleRid;
    }
    /**
     * @param roleRid the roleRid to set
     */
    public void setRoleRid(BigInteger roleRid) {
        this.roleRid = roleRid;
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
     * @return the count
     */
    public int getCount() {
        return count;
    }
    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }
    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }
    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }
    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
	
}
