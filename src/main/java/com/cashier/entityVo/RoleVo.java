package com.cashier.entityVo;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @ClassName: RoleVo
 * @description 角色表的Vo实体类
 *
 * @author dujiawei
 * @createDate 2019年7月4日
 */
public class RoleVo implements Serializable {

	/** UID  */
	private static final long serialVersionUID = 1L;
	/** 角色ID */
	private BigInteger id;
	/** 角色名称 */
	private String name;
	/** 所属店铺ID */
    private BigInteger shopId;
    /** 当前所在页 */
	private int page = 1;
	/** 每页显示条数 */
	private int limit = 5;
	/** 数据条数 */
	private Integer count;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getShopId() {
		return shopId;
	}
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + limit;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + page;
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
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
		RoleVo other = (RoleVo) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
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
		if (page != other.page)
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RoleVo [id=" + id + ", name=" + name + ", shopId=" + shopId + ", page=" + page + ", limit=" + limit
				+ ", count=" + count + "]";
	}

}
