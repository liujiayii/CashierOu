package com.cashier.entity;

import java.io.Serializable;
import java.math.BigInteger;
/**
 * 计量单位表实体类
 *
 * @author pangchong
 * @createDate 2018年9月12日 上午10:30
 */
public class Unit implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 计量单位id */
	private BigInteger id;
	/**计量单位名称 */
	private String name;
	/**是否可用,软删除（1：可用/2：不可用） */
	private Integer canUse;
	/**店铺id */
	private BigInteger shopId;
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
	public Integer getCanUse() {
		return canUse;
	}
	public void setCanUse(Integer canUse) {
		this.canUse = canUse;
	}
	public BigInteger getShopId() {
		return shopId;
	}
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((canUse == null) ? 0 : canUse.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Unit other = (Unit) obj;
		if (canUse == null) {
			if (other.canUse != null)
				return false;
		} else if (!canUse.equals(other.canUse))
			return false;
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
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Unit [id=").append(id).append(", name=").append(name).append(", canUse=").append(canUse)
				.append(", shopId=").append(shopId).append("]");
		return builder.toString();
	}
	
	
}
