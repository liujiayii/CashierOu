/**
 * @Title: UserOperation.java
 * @Package com.cashier.entity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Administrator
 * @date 2019年7月8日
 */
package com.cashier.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName: UserOperation
 * @description 用一句话描述这个类的作用
 * @author liujunkai
 * @createDate 2019年7月8日
 */
public class UserOperation implements Serializable{
    
    /**  字段的含义 */
    private static final long serialVersionUID = 1L;
    /** 操作记录id */
    private BigInteger id;
    /** 操作人账号 */
    private String userName;
    /** 操作人姓名 */
    private String name;
    /** 操作内容 */
    private String operatingContent;
    /** 操作时间 */
    private Timestamp time;
    /** 店铺名称 */
    private BigInteger shopId;
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOperatingContent() {
        return operatingContent;
    }
    public void setOperatingContent(String operatingContent) {
        this.operatingContent = operatingContent;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
    public BigInteger getShopId() {
        return shopId;
    }
    public void setShopId(BigInteger shopId) {
        this.shopId = shopId;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((operatingContent == null) ? 0 : operatingContent.hashCode());
        result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        UserOperation other = (UserOperation) obj;
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
        if (operatingContent == null) {
            if (other.operatingContent != null)
                return false;
        } else if (!operatingContent.equals(other.operatingContent))
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
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "UserOperation [id=" + id + ", userName=" + userName + ", name=" + name + ", operatingContent="
                + operatingContent + ", time=" + time + ", shopId=" + shopId + "]";
    }
    public UserOperation(BigInteger id, String userName, String name, String operatingContent, Timestamp time,
            BigInteger shopId) {
        super();
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.operatingContent = operatingContent;
        this.time = time;
        this.shopId = shopId;
    }
    public UserOperation() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
}
