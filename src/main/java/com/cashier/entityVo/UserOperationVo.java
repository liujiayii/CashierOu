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
    /** 操作人账号 */
    private String userName;
    /** 操作人姓名 */
    private String name;
    /** 操作内容 */
    private String operatingContent;
    /** 操作时间 */
    private Timestamp time;
    /** 店铺名称 */
    private String shopName;
    /** 店铺ID */
    private BigInteger ShopId;
	/** 页 */
	private int page ;
	/** 行 */
	private int limit ;
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
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public BigInteger getShopId() {
        return ShopId;
    }
    public void setShopId(BigInteger shopId) {
        ShopId = shopId;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ShopId == null) ? 0 : ShopId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + limit;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((operatingContent == null) ? 0 : operatingContent.hashCode());
        result = prime * result + page;
        result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
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
        UserOperationVo other = (UserOperationVo) obj;
        if (ShopId == null) {
            if (other.ShopId != null)
                return false;
        } else if (!ShopId.equals(other.ShopId))
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
        if (shopName == null) {
            if (other.shopName != null)
                return false;
        } else if (!shopName.equals(other.shopName))
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
        return "UserOperationVo [id=" + id + ", userName=" + userName + ", name=" + name + ", operatingContent="
                + operatingContent + ", time=" + time + ", shopName=" + shopName + ", ShopId=" + ShopId + ", page="
                + page + ", limit=" + limit + "]";
    }
    public UserOperationVo(BigInteger id, String userName, String name, String operatingContent, Timestamp time,
            String shopName, BigInteger shopId, int page, int limit) {
        super();
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.operatingContent = operatingContent;
        this.time = time;
        this.shopName = shopName;
        ShopId = shopId;
        this.page = page;
        this.limit = limit;
    }
    public UserOperationVo() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    
	
	
}
